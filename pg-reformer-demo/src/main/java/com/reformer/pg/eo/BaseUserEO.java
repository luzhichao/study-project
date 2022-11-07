package com.reformer.pg.eo;

import com.reformer.eo.BaseEO;
import lombok.Data;

/**
 * 用户基础信息EO
 *
 * @author LZC
 * @version 1.1.1
 * @date 2021-11-09
 **/
@Data
public class BaseUserEO extends BaseEO {
    private static final long serialVersionUID = 1L;

    /*** 真实姓名 **/
    private String realName;
    /*** 账号 **/
    private String username;
    /*** 密码 **/
    private String password;
    /*** 用户邮箱 **/
    private String email;
    /*** 手机 **/
    private String phone;
    /*** 所属机构ID **/
    private String orgId;
    /*** 用户性别：1男 0女 **/
    private String sex;
    /*** 头像地址 **/
    private String avatar;
    /*** 是否是系统用户，默认不是 **/
    private Boolean sysUser;
    /*** 办公电话 **/
    private String officePhone;
    /*** 职务 **/
    private String post;
    /*** 身份证号码 **/
    private String card;
    /*** 出生日期 **/
    private String birthDay;
    /*** 民族 **/
    private String nation;
    /*** 政治面貌 **/
    private String politicalStatus;
    /*** 紧急联络人 **/
    private String emergencyPerson;
    /*** 紧急联系电话 **/
    private String emergencyPhone;
    /*** 详细地址 **/
    private String address;
    /*** 所在地区 **/
    private String location;
    /*** 帐号状态：0正常 -1停用 **/
    private String status;
    /*** 备注 **/
    private String remark;

}
