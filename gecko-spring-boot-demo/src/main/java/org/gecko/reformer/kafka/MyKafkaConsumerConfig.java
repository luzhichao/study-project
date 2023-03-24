package org.gecko.reformer.kafka;

import org.gecko.reformer.config.KafkaConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author LZC
 * @version 1.2.0
 * @date 2023-03-24
 **/
@Configuration
public class MyKafkaConsumerConfig {

    @Resource
    private KafkaConsumerConfig kafkaConsumerConfig;

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> myFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaConsumerConfig.consumerFactory());
        // 设置为批量消费，每个批次数量在Kafka配置参数中设置ConsumerConfig.MAX_POLL_RECORDS_CONFIG
        factory.setBatchListener(true);
        // 消费者组中线程数量
        factory.setConcurrency(kafkaConsumerConfig.getConcurrency());
        // 拉取超时时间
        factory.getContainerProperties().setPollTimeout(kafkaConsumerConfig.getPollTimeout());
        // ContainerProperties.AckMode.MANUAL_IMMEDIATE
        factory.getContainerProperties().setAckMode(kafkaConsumerConfig.getAckMode());
        return factory;
    }
}
