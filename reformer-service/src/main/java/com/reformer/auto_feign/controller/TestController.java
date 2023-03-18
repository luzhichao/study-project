package com.reformer.auto_feign.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.reformer.annotation.WebController;
import com.reformer.auto_feign.controller.block.TestBlockHandler;
import com.reformer.auto_feign.dto.QueryDTO;
import com.reformer.auto_feign.feign.TestFeign;
import com.reformer.system.api.dto.UserDTO;
import com.reformer.system.api.dto.WechatAppletAuthDTO;
import com.reformer.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-05
 **/
@Api(tags = "test")
@WebController
@RequestMapping("/api/test-controller/")
public class TestController {

    @Resource
    private TestFeign testFeign;

    @PostMapping("/test0")
    @ApiOperation("test0")
    public Result test0(@RequestBody QueryDTO dto) {
        WechatAppletAuthDTO d = new WechatAppletAuthDTO();
        testFeign.saveWechatAppletAuth(d);

        final UserDTO result = testFeign.queryUserById(dto.getId());
        return Result.success(result);
    }

    @SentinelResource(value = "my-test", blockHandlerClass = TestBlockHandler.class, blockHandler = "testBlockHandler")
    @ApiOperation("test1")
    @PostMapping("/test1")
    public Result<String> test1() {
        return Result.success("123456");
    }

    //public Result<String> testBlockHandler(BlockException e) {
    //    return Result.error("操作过于频繁");
    //}
}