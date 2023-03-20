//package org.gecko.reformer.kafka;
//
//import cn.hutool.core.util.StrUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.producer.ProducerInterceptor;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.apache.kafka.clients.producer.RecordMetadata;
//import org.gecko.reformer.util.SpringUtils;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
///**
// * TODO
// *
// * @author LZC
// * @version 1.2.0
// * @date 2023-03-20
// **/
//@Slf4j
//@Component
//@DependsOn("springUtils")
//public class KafkaProducerInterceptor implements ProducerInterceptor<String, Object> {
//
//
//    /**
//     * 运行在用户主线程中，在消息被序列化之前调用
//     *
//     * @param record
//     * @return
//     */
//    @Override
//    public ProducerRecord<String, Object> onSend(ProducerRecord<String, Object> record) {
//        log.info("原始topic：{}", record.topic());
//        final String env = SpringUtils.getProperty("spring.profiles.active", String.class, StrUtil.EMPTY);
//
//        return record;
//        //return new ProducerRecord<String, Object>(env + StrUtil.UNDERLINE + record.topic(),
//        //        record.partition(), record.timestamp(), record.key(), record.value());
//    }
//
//
//    /**
//     * 在消息被应答之前或者消息发送失败时调用，通常在producer回调逻辑触发之前，运行在produer的io线程中
//     *
//     * @param metadata
//     * @param exception
//     */
//    @Override
//    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
//        log.info("实际topic：{}", metadata.topic());
//    }
//
//
//    /**
//     * 清理工作
//     */
//    @Override
//    public void close() {
//    }
//
//
//    /**
//     * 初始化工作
//     *
//     * @param configs
//     */
//    @Override
//    public void configure(Map<String, ?> configs) {
//
//    }
//}
//
