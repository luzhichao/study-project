package org.gecko.reformer.util;

import cn.hutool.core.util.StrUtil;
import com.ververica.cdc.debezium.DebeziumDeserializationSchema;
import com.ververica.cdc.debezium.JsonDebeziumDeserializationSchema;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.connector.source.Source;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.gecko.reformer.constant.FlinkConstants;
import org.gecko.reformer.constant.IFlinkConstants;
import org.gecko.reformer.dto.ReformerFlinkMongoDTO;
import org.gecko.reformer.dto.ReformerFlinkMySqlDTO;
import org.gecko.reformer.dto.ReformerFlinkPostgreSqlDTO;
import org.gecko.reformer.transform.MongodbDebeziumDeserializationSchema;

/**
 * flink 工具类
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-09
 **/
public final class FlinkUtils {
    private FlinkUtils() {
    }

    /**
     * 构建解析框架为json字符串类型的mysql数据源的DataStreamSource
     *
     * @param dto
     * @return org.apache.flink.streaming.api.datastream.DataStreamSource<java.lang.String>
     * @throws
     * @author LZC
     * @date 2023-03-09
     * @version 1.1.2
     **/
    public static DataStreamSource<String> getMysqlSource(ReformerFlinkMySqlDTO dto) {
        final JsonDebeziumDeserializationSchema schema = new JsonDebeziumDeserializationSchema(false, FlinkConstants.config);
        final DataStreamSource<String> source = getMysqlSource(dto, schema);
        return source;
    }

    /**
     * 构建指定解析框架的mysql数据源的DataStreamSource
     *
     * @param dto
     * @param schema 指定解析框架
     * @return org.apache.flink.streaming.api.datastream.DataStreamSource<T>
     * @throws
     * @author LZC
     * @date 2023-03-09
     * @version 1.1.2
     **/
    public static <T> DataStreamSource<T> getMysqlSource(ReformerFlinkMySqlDTO dto, DebeziumDeserializationSchema<T> schema) {
        final String sourceName = StrUtil.format(IFlinkConstants.MYSQL_SOURCE_NAME_TEMPLATE, dto.getJobId());
        Source<T, ?, ?> mySqlSource = SourceUtils.buildMysqlSource(dto, schema);
        final StreamExecutionEnvironment env = FlinkEnvUtils.getStreamExecute(dto);
        final DataStreamSource<T> source = env.fromSource(mySqlSource, WatermarkStrategy.noWatermarks(), sourceName);
        return source;
    }

    /**
     * 构建解析框架为json字符串类型的PostgresSQL数据源的DataStreamSource
     *
     * @param dto
     * @return org.apache.flink.streaming.api.datastream.DataStreamSource<java.lang.String>
     * @throws
     * @author LZC
     * @date 2023-03-09
     * @version 1.1.2
     **/
    public static DataStreamSource<String> getPgSource(ReformerFlinkPostgreSqlDTO dto) {
        final JsonDebeziumDeserializationSchema schema = new JsonDebeziumDeserializationSchema(false, FlinkConstants.config);
        final DataStreamSource<String> source = getPgSource(dto, schema);
        return source;
    }

    /**
     * 构建指定解析框架的PostgresSQL数据源的DataStreamSource
     *
     * @param dto
     * @param schema 指定解析框架
     * @return org.apache.flink.streaming.api.datastream.DataStreamSource<T>
     * @throws
     * @author LZC
     * @date 2023-03-09
     * @version 1.1.2
     **/
    public static <T> DataStreamSource<T> getPgSource(ReformerFlinkPostgreSqlDTO dto, DebeziumDeserializationSchema<T> schema) {
        SourceFunction<T> sourceFunction = SourceUtils.buildPgSource(dto, schema);
        final StreamExecutionEnvironment env = FlinkEnvUtils.getStreamExecute(dto);
        final DataStreamSource<T> source = env.addSource(sourceFunction);
        return source;
    }

    /**
     * 构建解析框架为json字符串类型的mongodb数据源的DataStreamSource
     *
     * @param dto
     * @return org.apache.flink.streaming.api.datastream.DataStreamSource<java.lang.String>
     * @throws
     * @author LZC
     * @date 2023-03-09
     * @version 1.1.2
     **/
    public static DataStreamSource<String> getMgSource(ReformerFlinkMongoDTO dto) {
        final MongodbDebeziumDeserializationSchema schema = new MongodbDebeziumDeserializationSchema(false, FlinkConstants.config);
        final DataStreamSource<String> source = getMgSource(dto, schema);
        return source;
    }

    /**
     * 构建指定解析框架的mongodb数据源的DataStreamSource
     *
     * @param dto
     * @param schema
     * @return org.apache.flink.streaming.api.datastream.DataStreamSource<T>
     * @throws
     * @author LZC
     * @date 2023-03-09
     * @version 1.1.2
     **/
    public static <T> DataStreamSource<T> getMgSource(ReformerFlinkMongoDTO dto, DebeziumDeserializationSchema<T> schema) {
        final SourceFunction<T> mongoSource = SourceUtils.buildMgSource(dto, schema);
        final StreamExecutionEnvironment env = FlinkEnvUtils.getStreamExecute(dto);
        final DataStreamSource<T> source = env.addSource(mongoSource);
        return source;
    }
}
