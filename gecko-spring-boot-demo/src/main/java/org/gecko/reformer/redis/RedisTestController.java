package org.gecko.reformer.redis;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.annotation.WebController;
import org.gecko.reformer.service.RedisService;
import org.gecko.reformer.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * TODO
 *
 * @author LZC
 * @version 1.2.0
 * @date 2023-03-23
 **/
@Slf4j
@Api(tags = "TestRedis")
@WebController
@RequestMapping("/redis/test/api/")
public class RedisTestController {

    @Resource
    private RedisService redisService;

    @SneakyThrows
    @ApiOperation("testCacheRedis")
    @PostMapping("/testCacheRedis")
    public Result<String> testCacheRedis(@RequestBody String key) {
        final CacheTestDTO1 dto = new CacheTestDTO1();
        dto.setName("测试");
        dto.setLocalDate(LocalDate.now());
        dto.setLocalTime(LocalTime.now());
        dto.setLocalDateTime(LocalDateTime.now());

        redisService.hSet("test", key, dto);
        return Result.success();
    }

    @SneakyThrows
    @ApiOperation("testGetCacheRedis")
    @PostMapping("/testGetCacheRedis")
    public Result testGetCacheRedis(@RequestBody String key) {
        final Object test = redisService.hGet("test", key);
        final CacheTestDTO2 result = JSON.parseObject(JSON.toJSONString(test), CacheTestDTO2.class);
        return Result.success(result);
    }
}
