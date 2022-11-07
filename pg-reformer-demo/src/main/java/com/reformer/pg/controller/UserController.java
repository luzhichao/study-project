package com.reformer.pg.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reformer.annotation.WebController;
import com.reformer.pg.dto.UserPageReqDTO;
import com.reformer.pg.eo.BaseUserEO;
import com.reformer.pg.eo.UserDeptEO;
import com.reformer.pg.eo.UserPageResEO;
import com.reformer.pg.eo.UserQueryDataEO;
import com.reformer.pg.mapper.UserMapper;
import com.reformer.utils.AccountUtils;
import com.reformer.utils.PageUtil;
import com.reformer.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户管理接口
 * 部分接口有数据权限
 *
 * @author LZC
 * @version 1.1.1
 * @date 2021-12-22
 **/
@Slf4j
@WebController
@Api(tags = "test")
@RequestMapping("/api/test/")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @ApiOperation("test1")
    @PostMapping("/test1")
    public Result<String> test1(@RequestBody UserPageReqDTO dto) {
        final String currentOrgId = AccountUtils.getCurrentOrgId();

        final List<UserDeptEO> uds = userMapper.listDeptUserByOrgIds(currentOrgId);

        final Page<Object> page = PageUtil.buildPage(dto);
        final UserQueryDataEO eo = BeanUtil.copyProperties(dto, UserQueryDataEO.class);
        final Page<UserPageResEO> result1 = userMapper.pageUserByCondition(page, eo);

        final List<String> orgIds = uds.stream().map(UserDeptEO::getOrgId).collect(Collectors.toList());
        final List<BaseUserEO> result2 = userMapper.listUserByRoleIds(orgIds);

        return Result.success();
    }

}
