package org.gecko.reformer.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author LZC
 * @version 1.2.0
 * @date 2023-03-18
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest//(classes = GeckoApplication.class)
//@DirtiesContext
@EmbeddedKafka(count = 3, ports = {9012, 9013, 9014}, partitions = 1, topics = {"embedded-topic"})
public class ProducerAndConsumerTests {

    @Resource
    @Qualifier("embeddedProducer")
    private KafkaTemplate embeddedProducer;

    //@Autowired
    //KafkaEmbedded kafkaEmbeded;

    //@Autowired
    KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    //@AfterEach
    //@SneakyThrows(InterruptedException.class)
    //public void sleep() {
    //    TimeUnit.SECONDS.sleep(5);
    //}


    @Test
    @Transactional
    public void sendMassage() {
        log.info("sendMassage");
        // 1.发送消息
        embeddedProducer.send("embedded-topic", "test-embedded-message"+ System.currentTimeMillis());
    }

    @KafkaListener(topics = "embedded-topic", containerFactory = "embeddedConsumerFactory")
    public void listen(ConsumerRecord<String, Object> record) {
        log.info("receive kafka message:{}", record);
    }


    //@Before
    //public void setUp() throws Exception {
    //    for (MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry.getListenerContainers()) {
    //        ContainerTestUtils.waitForAssignment(messageListenerContainer,
    //                kafkaEmbeded.getPartitionsPerTopic());
    //    }
    //}
}
