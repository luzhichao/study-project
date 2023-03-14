package org.gecko.reformer.factory;

import cn.hutool.core.bean.BeanUtil;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.gecko.reformer.config.ReformerFlinkCdcMongoProperties;
import org.gecko.reformer.dto.ReformerFlinkMongoDTO;
import org.gecko.reformer.sink.ReformerMongoSink;
import org.gecko.reformer.util.FlinkUtils;

/**
 * mongodb flink cdc工厂
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-08
 **/
public class FlinkCdcMongoFactory implements IFlinkCdcFactory<ReformerFlinkCdcMongoProperties> {

    @Override
    public StreamExecutionEnvironment buildEnv(ReformerFlinkCdcMongoProperties conf) {

        final ReformerFlinkMongoDTO dto = BeanUtil.copyProperties(conf, ReformerFlinkMongoDTO.class);
        DataStreamSource<String> source = FlinkUtils.getMgSource(dto);
        source.addSink(new ReformerMongoSink()).setParallelism(1);
        final StreamExecutionEnvironment env = source.getExecutionEnvironment();
        return env;
    }
}
