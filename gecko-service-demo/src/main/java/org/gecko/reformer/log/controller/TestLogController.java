package org.gecko.reformer.log.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.gecko.reformer.annotation.SysLog;
import org.gecko.reformer.annotation.WebController;
import org.gecko.reformer.constant.LogConstants;
import org.gecko.reformer.dto.IdDTO;
import org.gecko.reformer.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-05
 **/
@Api(tags = "LogFeign")
@WebController
@RequestMapping("/api/test-log-controller/")
public class TestLogController {

    @PostMapping("/testLog1")
    @ApiOperation(value = "测试日志1")
    @SysLog(operation = LogConstants.Operation.SELECT)
    public Result testLog1(@RequestBody IdDTO dto) {
        return Result.success("result");
    }

    @PostMapping("/testLog2")
    @ApiOperation(value = "测试日志2")
    @SysLog(operation = LogConstants.Operation.SELECT, ignore = true)
    public Result testLog2(@RequestBody IdDTO dto) {
        return Result.success("result");
    }

    @PostMapping("/testLog3")
    @ApiOperation(value = "测试日志3")
    @SysLog(operation = LogConstants.Operation.SELECT, ignorePrams = true)
    public Result testLog3(@RequestBody IdDTO dto) {
        return Result.success("result");
    }

    @PostMapping("/testLog4")
    @ApiOperation(value = "测试日志4")
    public Result testLog4(@RequestBody IdDTO dto) {
        return Result.success("result");
    }

    @PostMapping("/testLog5")
    @ApiOperation(value = "测试日志5")
    public void testLog5(@RequestBody IdDTO dto) {
    }
}
