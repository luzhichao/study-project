package org.gecko.reformer.shrading.controller;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.annotation.WebController;
import org.gecko.reformer.context.DataRuleContext;
import org.gecko.reformer.shrading.dto.VehicleDTO;
import org.gecko.reformer.shrading.entity.User;
import org.gecko.reformer.shrading.entity.VehicleRealLocation;
import org.gecko.reformer.shrading.mapper.UserMapper;
import org.gecko.reformer.shrading.mapper.VehicleRealLocationMapper;
import org.gecko.reformer.util.DateUtils;
import org.gecko.reformer.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@Api(tags = "TestShrading")
@RequestMapping("/api/")
public class TestShradingController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private VehicleRealLocationMapper vehicleRealLocationMapper;

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

    @ApiOperation("test1")
    @PostMapping("/test1")
    public Result<String> test1(@RequestBody VehicleDTO dto) {
        DataRuleContext.setDataRules(Lists.newArrayList("123"));
        List<VehicleRealLocation> list = new LambdaQueryChainWrapper<>(vehicleRealLocationMapper)
                .between(VehicleRealLocation::getDateTime, dto.getStartTime(), dto.getEndTime())
                .list();
        list.forEach(d -> log.info("{}", d));
        return Result.success();
    }

    @ApiOperation("test2")
    @PostMapping("/test2")
    public Result<String> test2(@RequestBody VehicleDTO dto) {
        DataRuleContext.setDataRules(Lists.newArrayList("123"));
        List<VehicleRealLocation> list = new LambdaQueryChainWrapper<>(vehicleRealLocationMapper)
                .eq(VehicleRealLocation::getDateTime, dto.getStartTime())
                .list();
        list.forEach(d -> log.info("{}", d));
        return Result.success();
    }
}
