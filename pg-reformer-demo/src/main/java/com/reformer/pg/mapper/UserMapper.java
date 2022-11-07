package com.reformer.pg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reformer.annotation.AutoDataRule;
import com.reformer.pg.entity.User;
import com.reformer.pg.eo.BaseUserEO;
import com.reformer.pg.eo.UserDeptEO;
import com.reformer.pg.eo.UserPageResEO;
import com.reformer.pg.eo.UserQueryDataEO;
import org.apache.ibatis.annotations.Param;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 用户表 数据处理Mapper接口
 *
 * @author LZC
 * @version 1.1.1
 * @date 2021-12-22
 **/
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询机构部门用户信息，人员未去重按关联部门查询<br/>
     * 参数必传
     *
     * @param orgId
     * @return java.util.List<com.reformer.system.domain.eo.user.UserDeptEO>
     * @throws
     * @author LZC
     * @date 2022-04-12
     * @version 1.1.1
     **/
    @AutoDataRule
    List<UserDeptEO> listDeptUserByOrgIds(@Param("orgId") String orgId);

    /**
     * 根据角色ID集合查询用户
     *
     * @param roleIds
     * @return java.util.List<com.reformer.system.domain.eo.user.BasicUserEO>
     * @throws
     * @author LZC
     * @date 2022-06-30
     * @version 1.1.2
     **/
    @AutoDataRule
    List<BaseUserEO> listUserByRoleIds(@Param("roleIds") @NotEmpty List<String> roleIds);

    /**
     * 根据机构部门ID集合分页查询人员信息，人员去重<br/>
     * 参数为空查所有
     *
     * @param page
     * @param query
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.reformer.system.domain.eo.user.DeptUserEO>
     * @throws
     * @author LZC
     * @date 2022-04-12
     * @version 1.1.1
     **/
    @AutoDataRule
    Page<UserPageResEO> pageUserByCondition(@Param("page") Page page, @Param("query") UserQueryDataEO query);
}
