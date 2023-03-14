package org.gecko.reformer.factory;

import cn.hutool.core.bean.BeanUtil;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.gecko.reformer.config.ReformerFlinkCdcMySqlProperties;
import org.gecko.reformer.dto.ReformerFlinkMySqlDTO;
import org.gecko.reformer.sink.ReformerSqlSink;
import org.gecko.reformer.util.FlinkUtils;

/**
 * mysql flink cdc工厂
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-07
 **/
public class FlinkCdcMySqlFactory implements IFlinkCdcFactory<ReformerFlinkCdcMySqlProperties> {

    @Override
    public StreamExecutionEnvironment buildEnv(ReformerFlinkCdcMySqlProperties conf) {
        final ReformerFlinkMySqlDTO dto = BeanUtil.copyProperties(conf, ReformerFlinkMySqlDTO.class);
        DataStreamSource<String> source = FlinkUtils.getMysqlSource(dto);
        source.addSink(new ReformerSqlSink()).setParallelism(1);
        final StreamExecutionEnvironment env = source.getExecutionEnvironment();
        return env;
    }
}
