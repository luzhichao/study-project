package org.gecko.reformer.jackson_date_format.controller;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.gecko.reformer.annotation.WebController;
import org.gecko.reformer.jackson_date_format.entity.User;
import org.gecko.reformer.jackson_date_format.mapper.UserMapper;
import org.gecko.reformer.jackson_date_format.vo.UserVO;
import org.gecko.reformer.util.CollUtils;
import org.gecko.reformer.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-12-26
 **/
@Api(tags = "test")
@WebController
@RequestMapping("/jackson/api/")
public class TestController {

    @Resource
    private UserMapper userMapper;

    @SneakyThrows
    @ApiOperation("testJacksonDataFormat")
    @PostMapping("/testJacksonDataFormat")
    public Result<List<UserVO>> testJacksonDataFormat() {
        final List<User> list = new LambdaQueryChainWrapper<>(userMapper).list();
        final List<UserVO> result = CollUtils.convertListType(list, UserVO.class);
        return Result.success(result);
    }
}
