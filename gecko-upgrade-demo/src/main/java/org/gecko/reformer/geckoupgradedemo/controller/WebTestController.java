package org.gecko.reformer.geckoupgradedemo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.annotation.WebController;
import org.gecko.reformer.contexts.DataRuleContext;
import org.gecko.reformer.dto.PageDTO;
import org.gecko.reformer.geckoupgradedemo.dto.UserDTO;
import org.gecko.reformer.geckoupgradedemo.entity.Expert;
import org.gecko.reformer.geckoupgradedemo.handler.ChangeDTO;
import org.gecko.reformer.geckoupgradedemo.service.IExpertService;
import org.gecko.reformer.util.ChangedUtils;
import org.gecko.reformer.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-02-01
 **/
@Slf4j
@Api(tags = "test")
@WebController
@RequestMapping("/api/gecko/web/")
public class WebTestController {

    @Resource
    private IExpertService expertService;

    @SneakyThrows
    @ApiOperation("test1")
    @PostMapping("/test1")
    public Result test1(@RequestBody PageDTO dto) {

        DataRuleContext.setDataRules(Lists.newArrayList("1234"));
        final Page<Expert> page = expertService.pageExpert(dto);

        return Result.success(page);
    }

    @SneakyThrows
    @ApiOperation("test2")
    @PostMapping("/test2")
    public Result test2() {

        final Date date = new Date();

        UserDTO dto1 = new UserDTO();
        dto1.setRealName("123");
        dto1.setUsername("username1");
        dto1.setPhone("phone");
        dto1.setBirthDay(new Date());

        Thread.sleep(3000);

        UserDTO dto2 = new UserDTO();
        dto2.setRealName("321");
        dto2.setUsername("username");
        dto2.setPhone("phone");
        dto2.setBirthDay(new Date());

        String param = "123456789";
        final ChangeDTO dto = new ChangeDTO();
        dto.setValue1("value1==");
        dto.setValue2("value2==");
        Map<String, String> map = Maps.newConcurrentMap();
        map.put("key1", "value1");
        map.put("key2", "value2");


        ChangedUtils.checkObj(dto1, dto2, dto);

        return Result.success();
    }

}
