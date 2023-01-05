package com.reformer.processorreformertest.controller;

import com.reformer.annotation.WebController;
import com.reformer.processorreformertest.dto.QueryDTO;
import com.reformer.processorreformertest.feign.TestFeign;
import com.reformer.system.api.dto.UserDTO;
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

        @PostMapping("/test")
        @ApiOperation(value = "测试")
        public Result test(@RequestBody QueryDTO dto) {

            final UserDTO result = testFeign.queryUserById(dto.getId());

            return Result.success(result);
        }
}
