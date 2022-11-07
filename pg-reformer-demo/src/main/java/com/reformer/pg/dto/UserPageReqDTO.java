package com.reformer.pg.dto;

import com.reformer.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户分页查询DTO
 *
 * @author LZC
 * @version 1.1.1
 * @date 2021-11-09
 **/
@Data
@ApiModel(description = "用户分页查询DTO")
public class UserPageReqDTO extends PageDTO {
    private static final long serialVersionUID = 1L;

    /*** 用户名、真实名 **/
    @ApiModelProperty("用户名、真实名")
    private String name;
    /*** 机构ID **/
    @ApiModelProperty("机构ID")
    private String orgId;

}
