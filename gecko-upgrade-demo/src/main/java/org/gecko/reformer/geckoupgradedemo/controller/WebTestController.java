package org.gecko.reformer.geckoupgradedemo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.annotation.WebController;
import org.gecko.reformer.contexts.DataRuleContext;
import org.gecko.reformer.dto.PageDTO;
import org.gecko.reformer.geckoupgradedemo.entity.Expert;
import org.gecko.reformer.geckoupgradedemo.entity.User;
import org.gecko.reformer.geckoupgradedemo.service.IExpertService;
import org.gecko.reformer.geckoupgradedemo.service.ITestService;
import org.gecko.reformer.util.PageUtil;
import org.gecko.reformer.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

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

}
