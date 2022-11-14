package org.gecko.reformer.poi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 参数对象
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-09-09
 **/
@Data
@ApiModel
public class TestDTO implements Serializable {

    /*** 名称 **/
    @ApiModelProperty("名称")
    private String name;
    /*** 开始时间  YYYY-MM-DD HH:MM:SS **/
    @ApiModelProperty("开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    /*** 结束时间 **/
    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

}
