package org.gecko.reformer.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.Map;

/**
 * TODO
 *
 * @author LZC
 * @version 1.2.0
 * @date 2023-03-18
 **/
@Configuration
@RequiredArgsConstructor
public class EmbeddedKafkaConfig {


    private final EmbeddedKafkaBroker broker;

    @Bean("embeddedProducer")
    public KafkaTemplate embeddedProducer() {

        //1.获取Producer配置
        Map<String, Object> props = KafkaTestUtils.producerProps(broker);
        props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "abc");
        //2.返回Producer实例
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(props));
    }

    @Bean("embeddedConsumerFactory")
    public KafkaListenerContainerFactory embeddedConsumerFactory() {

        //1.定义基础属性
        String group = "embeddedGroup";
        String autoCommit = "true";
        //2.获取Consumer配置
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps(group, autoCommit, broker);
        //3.创建工厂
        DefaultKafkaConsumerFactory<Object, Object> consumerFactory = new DefaultKafkaConsumerFactory<>(consumerProps);
        //4.创建Consumer工厂
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        //5.返回
        return factory;
    }

}
