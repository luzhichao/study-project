package org.gecko.reformer.factory;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.gecko.reformer.config.ReformerFlinkCdcBaseProperties;

/**
 * flink cdc工厂
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-07
 **/
public interface IFlinkCdcFactory<C extends ReformerFlinkCdcBaseProperties> {

    /**
     * 通过配置构建flink evn
     *
     * @param conf
     * @return org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
     * @throws
     * @author LZC
     * @date 2023-03-07
     * @version 1.1.2
     **/
    StreamExecutionEnvironment buildEnv(C conf);
}
