package org.gecko.reformer.controller;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.google.common.collect.Lists;
import org.gecko.reformer.annotation.WebController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.contexts.DataRuleContext;
import org.gecko.reformer.entity.User;
import org.gecko.reformer.mapper.UserMapper;
import org.gecko.reformer.util.DateUtils;
import org.gecko.reformer.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author LZC
 * @version 1.0.0
 * @date 2022-06-23
 **/
@Slf4j
@WebController
@Api(tags = "test")
@RequestMapping("/api/")
public class TestController {

    @Resource
    private UserMapper userMapper;

    @ApiOperation("test")
    @PostMapping("/test")
    public Result<String> getDateTypeDown() {
        DataRuleContext.setDataRules(Lists.newArrayList("123"));
        Date one = DateUtils.parse("2022-05-05", DateUtils.YYYY_MM_DD);
        Date two = DateUtils.parse("2022-06-08", DateUtils.YYYY_MM_DD);
        List<User> list = new LambdaQueryChainWrapper<>(userMapper)
                .between(User::getBirthday, one, two)
                .list();
        list.forEach(user -> log.info("{}", user));
        return Result.success();
    }
}
