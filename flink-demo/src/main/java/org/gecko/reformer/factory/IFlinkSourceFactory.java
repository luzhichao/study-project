//package org.gecko.reformer.factory;
//
//import com.ververica.cdc.debezium.DebeziumDeserializationSchema;
//import org.apache.flink.api.connector.source.Source;
//import org.gecko.reformer.config.ReformerFlinkCdcBaseProperties;
//
///**
// * flink source工厂
// *
// * @author LZC
// * @version 1.1.2
// * @date 2023-03-07
// **/
//public interface IFlinkSourceFactory<T, C extends ReformerFlinkCdcBaseProperties> {
//
//    /**
//     * 构建Source
//     *
//     * @param conf
//     * @param schema
//     * @return org.apache.flink.api.connector.source.Source<T, ?, ?>
//     * @throws
//     * @author LZC
//     * @date 2023-03-09
//     * @version 1.1.2
//     **/
//    Source<T, ?, ?> buildSource(C conf, DebeziumDeserializationSchema<T> schema);
//}
