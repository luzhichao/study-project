//package org.gecko.reformer.factory;
//
//import com.ververica.cdc.debezium.DebeziumDeserializationSchema;
//import org.apache.flink.streaming.api.functions.source.SourceFunction;
//import org.gecko.reformer.config.ReformerFlinkCdcBaseProperties;
//
///**
// * flink source function工厂
// *
// * @author LZC
// * @version 1.1.2
// * @date 2023-03-07
// **/
//public interface IFlinkSourceFunctionFactory<T, C extends ReformerFlinkCdcBaseProperties> {
//
//    /**
//     * 构建SourceFunction
//     *
//     * @param conf
//     * @param schema
//     * @return org.apache.flink.streaming.api.functions.source.SourceFunction<T>
//     * @throws
//     * @author LZC
//     * @date 2023-03-08
//     * @version 1.1.2
//     **/
//    SourceFunction<T> buildSourceFunction(C conf, DebeziumDeserializationSchema<T> schema);
//}
