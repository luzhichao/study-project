package org.gecko.reformer.springbootdemo.controller;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.annotation.WebController;
import org.gecko.reformer.vo.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TestLog
 *
 * @author LZC
 * @version 1.2.0
 * @date 2023-05-17
 **/
@Slf4j
@Api(tags = "TestLog")
@WebController
@RequestMapping("/log/api/")
public class LogTestController {

    @SneakyThrows
    @ApiOperation("testLogPrint")
    @PostMapping("/testLogPrint")
    public Result<String> testLogPrint() {
        log.info("======info======");
        log.debug("======debug======");
        log.error("======error======");
        return Result.success();
    }

    @Value("${aa:}")
    private String aa;

    @Value("${logging.config:}")
    private String logConfig;

    @SneakyThrows
    @ApiOperation("testConfig")
    @PostMapping("/testConfig")
    public Result<String> testConfig() {
        final String format = StrUtil.format("aa==={}, logConfig==={}", aa, logConfig);
        return Result.success(format);
    }

}
