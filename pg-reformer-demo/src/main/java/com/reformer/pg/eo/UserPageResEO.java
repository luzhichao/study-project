package com.reformer.pg.eo;

import lombok.Data;

/**
 * 用户分页Res EO
 *
 * @author LZC
 * @version 1.1.1
 * @date 2021-12-22
 **/
@Data
public class UserPageResEO extends BaseUserEO {
    private static final long serialVersionUID = 1L;

    /*** 所属机构名称 **/
    private String orgName;
}