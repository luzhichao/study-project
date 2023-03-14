package org.gecko.reformer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.annotation.WebController;
import org.gecko.reformer.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-10
 **/
@Slf4j
@Api(tags = "TestFlink")
@WebController
@RequestMapping("/flink/api/")
public class TestFlinkController {

    @SneakyThrows
    @ApiOperation("testFlink")
    @GetMapping("/testFlink")
    public Result<String> testFlink() {
        log.info("===controller===testFlink====");
        return Result.success();
    }

}
