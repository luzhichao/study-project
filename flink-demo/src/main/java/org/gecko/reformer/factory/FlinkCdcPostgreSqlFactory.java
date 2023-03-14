package org.gecko.reformer.factory;

import cn.hutool.core.bean.BeanUtil;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.gecko.reformer.config.ReformerFlinkCdcPostgreSqlProperties;
import org.gecko.reformer.dto.ReformerFlinkPostgreSqlDTO;
import org.gecko.reformer.sink.ReformerSqlSink;
import org.gecko.reformer.util.FlinkUtils;

/**
 * postgre sql flink cdc工厂
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-07
 **/
public class FlinkCdcPostgreSqlFactory implements IFlinkCdcFactory<ReformerFlinkCdcPostgreSqlProperties> {

    @Override
    public StreamExecutionEnvironment buildEnv(ReformerFlinkCdcPostgreSqlProperties conf) {
        final ReformerFlinkPostgreSqlDTO dto = BeanUtil.copyProperties(conf, ReformerFlinkPostgreSqlDTO.class);
        DataStreamSource<String> source = FlinkUtils.getPgSource(dto);
        source.addSink(new ReformerSqlSink()).setParallelism(1);
        final StreamExecutionEnvironment env = source.getExecutionEnvironment();
        return env;
    }
}
