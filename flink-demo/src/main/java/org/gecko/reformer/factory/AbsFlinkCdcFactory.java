//package org.gecko.reformer.factory;
//
//import cn.hutool.core.bean.BeanUtil;
//import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
//import org.gecko.reformer.config.ReformerFlinkCdcBaseProperties;
//import org.gecko.reformer.dto.ReformerFlinkBaseDTO;
//import org.gecko.reformer.util.FlinkEnvUtils;
//
///**
// * flink cdc抽象工厂
// *
// * @author LZC
// * @version 1.1.2
// * @date 2023-03-08
// **/
//public abstract class AbsFlinkCdcFactory<C extends ReformerFlinkCdcBaseProperties> implements IFlinkCdcFactory<C> {
//
//    /**
//     * 获取执行流环境
//     *
//     * @param conf
//     * @return org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
//     * @throws
//     * @author LZC
//     * @date 2023-03-08
//     * @version 1.1.2
//     **/
//    protected StreamExecutionEnvironment getEnv(C conf) {
//        final ReformerFlinkBaseDTO dto = BeanUtil.copyProperties(conf, ReformerFlinkBaseDTO.class);
//        return FlinkEnvUtils.getStreamExecute(dto);
//    }
//}
