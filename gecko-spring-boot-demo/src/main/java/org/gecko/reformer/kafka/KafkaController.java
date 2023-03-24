package org.gecko.reformer.kafka;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.annotation.SysLog;
import org.gecko.reformer.annotation.WebController;
import org.gecko.reformer.constant.LogConstants;
import org.gecko.reformer.service.IKafkaService;
import org.gecko.reformer.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * 发送消息
 *
 * @author LZC
 * @version 1.2.0
 * @date 2023-03-17
 **/
@Slf4j
@Api(tags = "TestKafka")
@WebController
@RequestMapping("/kafka/test/api/")
public class KafkaController {

    @Resource
    private IKafkaService kafkaService;

    @SneakyThrows
    @SysLog(operation = LogConstants.Operation.OTHER, ignore = true)
    @ApiOperation("testKafkaSendMassage")
    @PostMapping("/testKafkaSendMassage")
    public Result<String> testKafkaSendMassage(@RequestBody String massage) {
        kafkaService.send("test-kafka", massage);
        return Result.success();
    }

    @SneakyThrows
    @SysLog(operation = LogConstants.Operation.OTHER, ignore = true)
    @ApiOperation("testKafkaSendObj")
    @PostMapping("/testKafkaSendObj")
    public Result<String> testKafkaSendObj(@RequestBody String massage) {
        final KafkaDTO dto = new KafkaDTO();
        dto.setTopic("test-kafka-send-obj");
        dto.setMassage(massage);
        dto.setDate(new Date());
        dto.setLocalDate(LocalDate.now());
        dto.setLocalTime(LocalTime.now());
        dto.setLocalDateTime(LocalDateTime.now());

        kafkaService.send("test-kafka-send-obj", dto);
        return Result.success();
    }

    @SneakyThrows
    @SysLog(operation = LogConstants.Operation.OTHER, ignore = true)
    @ApiOperation("testKafkaFixedTopic1")
    @PostMapping("/testKafkaFixedTopic1")
    public Result<String> testKafkaFixedTopic1(@RequestBody String massage) {
        final KafkaDTO dto = new KafkaDTO();
        dto.setTopic("test-kafka-fixed-topic-obj1");
        dto.setMassage(massage);
        dto.setDate(new Date());
        dto.setLocalDate(LocalDate.now());
        dto.setLocalTime(LocalTime.now());
        dto.setLocalDateTime(LocalDateTime.now());

        kafkaService.send("test-kafka-fixed-topic-obj1", dto);
        return Result.success();
    }

    @SneakyThrows
    @SysLog(operation = LogConstants.Operation.OTHER, ignore = true)
    @ApiOperation("testKafkaFixedTopic2")
    @PostMapping("/testKafkaFixedTopic2")
    public Result<String> testKafkaFixedTopic2(@RequestBody String massage) {
        final KafkaDTO dto = new KafkaDTO();
        dto.setTopic("test-kafka-fixed-topic-obj2");
        dto.setMassage(massage);
        dto.setDate(new Date());
        dto.setLocalDate(LocalDate.now());
        dto.setLocalTime(LocalTime.now());
        dto.setLocalDateTime(LocalDateTime.now());

        kafkaService.send("test-kafka-fixed-topic-obj2", dto);
        return Result.success();
    }
}
