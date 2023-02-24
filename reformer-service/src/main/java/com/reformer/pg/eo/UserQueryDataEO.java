package com.reformer.pg.eo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户查询数据EO对象
 *
 * @author LZC
 * @version 1.1.1
 * @date 2021-12-30
 **/
@Data
public class UserQueryDataEO implements Serializable {
    private static final long serialVersionUID = 1L;
    /*** 组织、部门ID **/
    private List<String> orgIds;
    /*** 用户名、真实名 **/
    private String name;
}
