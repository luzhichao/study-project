package org.gecko.reformer.kafka;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.annotation.WebController;
import org.gecko.reformer.service.IKafkaService;
import org.gecko.reformer.vo.Result;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

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
    @ApiOperation("testKafkaSendMassage")
    @PostMapping("/testKafkaSendMassage")
    public Result<String> testKafkaSendMassage(@RequestBody String massage) {
        final ListenableFuture<SendResult<String, Object>> send = kafkaService.send("test-kafka", massage);
        return Result.success();
    }
}
