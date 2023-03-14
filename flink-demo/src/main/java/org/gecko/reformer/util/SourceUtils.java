package org.gecko.reformer.util;

import cn.hutool.core.util.ArrayUtil;
import com.ververica.cdc.connectors.mongodb.MongoDBSource;
import com.ververica.cdc.connectors.mysql.source.MySqlSource;
import com.ververica.cdc.connectors.postgres.PostgreSQLSource;
import com.ververica.cdc.debezium.DebeziumDeserializationSchema;
import org.apache.flink.api.connector.source.Source;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.gecko.reformer.constant.FlinkConstants;
import org.gecko.reformer.dto.ReformerFlinkMongoDTO;
import org.gecko.reformer.dto.ReformerFlinkMySqlDTO;
import org.gecko.reformer.dto.ReformerFlinkPostgreSqlDTO;

import java.time.Duration;

/**
 * 数据源工具类
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-09
 **/
final class SourceUtils {
    private SourceUtils() {
    }

    /**
     * 构建mysql数据源
     *
     * @param dto
     * @param schema
     * @return org.apache.flink.api.connector.source.Source<T, ?, ?>
     * @throws
     * @author LZC
     * @date 2023-03-09
     * @version 1.1.2
     **/
    protected static <T> Source<T, ?, ?> buildMysqlSource(ReformerFlinkMySqlDTO dto, DebeziumDeserializationSchema<T> schema) {
        return MySqlSource.<T>builder()
                .hostname(dto.getHostname())
                .port(dto.getPort())
                .databaseList(ArrayUtil.toArray(dto.getDatabases(), String.class))
                .tableList(ArrayUtil.toArray(dto.getTables(), String.class))
                .username(dto.getUsername())
                .password(dto.getPassword())
                .serverTimeZone(dto.getServerTimeZone())
                .connectionPoolSize(dto.getConnectionPoolSize())
                .heartbeatInterval(Duration.ofSeconds(dto.getHeartbeatInterval()))
                .connectMaxRetries(dto.getConnectMaxRetries())
                .connectTimeout(Duration.ofSeconds(dto.getConnectTimeout()))
                .deserializer(schema)
                .debeziumProperties(FlinkConstants.properties)
                .startupOptions(dto.getOptionMode().getOption())
                .build();
    }

    /**
     * 构建postgreSQL数据源
     *
     * @param dto
     * @param schema
     * @return org.apache.flink.streaming.api.functions.source.SourceFunction<T>
     * @throws
     * @author LZC
     * @date 2023-03-09
     * @version 1.1.2
     **/
    protected static <T> SourceFunction<T> buildPgSource(ReformerFlinkPostgreSqlDTO dto, DebeziumDeserializationSchema<T> schema) {
        return PostgreSQLSource.<T>builder()
                .hostname(dto.getHostname())
                .port(dto.getPort())
                .database(dto.getDatabase())
                .schemaList(ArrayUtil.toArray(dto.getSchemas(), String.class))
                .tableList(ArrayUtil.toArray(dto.getTables(), String.class))
                .username(dto.getUsername())
                .password(dto.getPassword())
                .decodingPluginName(dto.getPluginName().getCode())
                .slotName(dto.getSlotName())
                .deserializer(schema)
                .debeziumProperties(FlinkConstants.properties)
                .build();
    }

    /**
     * 构建mongodb数据源
     *
     * @param dto
     * @param schema
     * @return org.apache.flink.streaming.api.functions.source.SourceFunction<T>
     * @throws
     * @author LZC
     * @date 2023-03-09
     * @version 1.1.2
     **/
    protected static <T> SourceFunction<T> buildMgSource(ReformerFlinkMongoDTO dto, DebeziumDeserializationSchema<T> schema) {
        return MongoDBSource.<T>builder()
                .hosts(dto.getHosts())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .databaseList(ArrayUtil.toArray(dto.getDatabases(), String.class))
                .collectionList(ArrayUtil.toArray(dto.getCollects(), String.class))
                .errorsTolerance(dto.getErrorsTolerance().getCode())
                .deserializer(schema)
                .batchSize(dto.getBatchSize())
                .pollMaxBatchSize(dto.getPollMaxBatchSize())
                .connectionOptions(dto.getConnectionOptions())
                .pollAwaitTimeMillis(dto.getAwaitTime())
                .heartbeatIntervalMillis(dto.getHeartbeatInterval())
                .copyExisting(dto.getCopyExisting())
                .build();
    }
}
