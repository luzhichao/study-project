package org.gecko.reformer.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 消费者监听处理类
 * ackMode设置为MANUAL或MANUAL_IMMEDIATE则必须要手动提交
 *
 * @author LZC
 * @version 1.2.0
 * @date 2023-03-17
 **/
@Slf4j
@Component
public class ConsumerHandler {

    /**
     * 非批量消费，必须手动提交
     * ack-mode=MANUAL或MANUAL_IMMEDIATE
     * batch-listener=false
     *
     * @param record
     * @param ack
     * @return void
     * @throws
     * @author LZC
     * @date 2023-03-18
     * @version 1.2.0
     **/
    //@KafkaListener(topics = {"test-kafka"})
    //public void listenSingleString(ConsumerRecord<String, Object> record, Acknowledgment ack) {
    //    log.info("topic#test-kafka===massage=={}", record.toString());
    //    ack.acknowledge();
    //}

    /**
     * 非批量消费，手动提交
     * ack-mode=MANUAL或MANUAL_IMMEDIATE
     * batch-listener=false
     *
     * @param record
     * @param ack
     * @return void
     * @throws
     * @author LZC
     * @date 2023-03-18
     * @version 1.2.0
     **/
    //@KafkaListener(topics = {"test-kafka"})
    //public void listenSingleString(String record, Acknowledgment ack) {
    //    log.info("topic#test-kafka===massage=={}", record);
    //    ack.acknowledge();
    //}

    /**
     * 批量消费，必须手动提交
     * ack-mode=MANUAL或MANUAL_IMMEDIATE
     * batch-listener=true
     *
     * @param record
     * @param ack
     * @return void
     * @throws
     * @author LZC
     * @date 2023-03-18
     * @version 1.2.0
     **/
    //@KafkaListener(topics = {"test-kafka"})
    //public void listenBatchRecord(List<ConsumerRecord<String, Object>> record, Acknowledgment ack) {
    //    log.info("topic#test-kafka===massage=={}", record.toString());
    //    ack.acknowledge();
    //}

    /**
     * 批量消费，必须手动提交
     * ack-mode=MANUAL或MANUAL_IMMEDIATE
     * batch-listener=true
     *
     * @param record
     * @param ack
     * @return void
     * @throws
     * @author LZC
     * @date 2023-03-18
     * @version 1.2.0
     **/
    //@KafkaListener(topics = {"test-kafka"})
    //public void listenBatchString(List<String> record, Acknowledgment ack) {
    //    log.info("topic#test-kafka===massage=={}", record.toString());
    //    ack.acknowledge();
    //}

    /**
     * 批量消费，自动提交
     * ack-mode!=MANUAL或MANUAL_IMMEDIATE
     * batch-listener=true
     * spring.kafka.consumer.enable-auto-commit=true或false
     * groupId = "groupId_#{T(System).currentTimeMillis()}"
     *
     * @param record
     * @return void
     * @throws
     * @author LZC
     * @date 2023-03-18
     * @version 1.2.0
     **/
    @KafkaListener(topics = {"test-kafka"})
    public void listenBatchStringAutoCommit(List<String> record) {
        log.info("topic#test-kafka===massage=={}", record.toString());
    }

    /**
     * 批量消费，自动提交
     * ack-mode!=MANUAL或MANUAL_IMMEDIATE
     * batch-listener=true
     * spring.kafka.consumer.enable-auto-commit=true或false
     *
     * @param record
     * @return void
     * @throws
     * @author LZC
     * @date 2023-03-18
     * @version 1.2.0
     **/
    //@KafkaListener(topics = {"test-kafka"}, groupId = "test_groupId")
    //public void listenBatchRecordAutoCommit(List<ConsumerRecord<String, Object>> record) {
    //    log.info("topic#test-kafka===massage=={}", record.toString());
    //}
}
