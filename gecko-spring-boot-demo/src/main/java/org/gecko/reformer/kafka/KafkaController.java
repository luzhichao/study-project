package org.gecko.reformer.kafka;

import cn.hutool.core.util.StrUtil;
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
    public Result<String> testKafkaSendMassage(@RequestBody SendDTO dto) {
        final KafkaDTO send = new KafkaDTO();
        send.setTopic(dto.getTopic());
        send.setMassage(dto.getMassage());
        send.setDate(new Date());
        send.setLocalDate(LocalDate.now());
        send.setLocalTime(LocalTime.now());
        send.setLocalDateTime(LocalDateTime.now());
        kafkaService.send(dto.getTopic(), send);
        return Result.success();
    }

    @SneakyThrows
    @SysLog(operation = LogConstants.Operation.OTHER, ignore = true)
    @ApiOperation("testKafkaBatchSendMassage")
    @PostMapping("/testKafkaBatchSendMassage")
    public Result<String> testKafkaBatchSendMassage(@RequestBody SendDTO dto) {
        for (int i = 0; i < 10; i++) {
            final KafkaDTO send = new KafkaDTO();
            send.setTopic(dto.getTopic());
            send.setMassage(dto.getMassage() + StrUtil.toString(i));
            send.setDate(new Date());
            send.setLocalDate(LocalDate.now());
            send.setLocalTime(LocalTime.now());
            send.setLocalDateTime(LocalDateTime.now());
            kafkaService.send(dto.getTopic(), send);
        }
        return Result.success();
    }
}
