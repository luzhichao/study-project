package com.reformer.pg.eo;

import lombok.Data;

/**
 * 用户部门关系 EO
 *
 * @author LZC
 * @version 1.1.1
 * @date 2021-12-22
 **/
@Data
public class UserDeptEO extends BaseUserEO {
    private static final long serialVersionUID = 1L;

    /*** 部门ID **/
    private String deptId;
}