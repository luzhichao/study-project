package org.gecko.reformer.job.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 自定义调度任务参数对象
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-09-09
 **/
@Data
@ApiModel
public class JobDTO implements Serializable {

    /*** 任务分组 **/
    @ApiModelProperty("任务分组")
    private String group;
    /*** 任务名称 **/
    @ApiModelProperty("任务名称")
    private String name;
    /*** 任务优先级 **/
    @ApiModelProperty("任务优先级")
    private Integer priority;
    /*** 执行定时任务需要附加的参数数据 **/
    @ApiModelProperty("执行定时任务需要附加的参数数据")
    private Object paramData;
    /*** 任务开始执行时间  YYYY-MM-DD HH:MM:SS **/
    @ApiModelProperty("任务开始执行时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    /*** 任务重复间隔（分钟）-仅需要按周期多次调度时有效 **/
    @ApiModelProperty("任务重复间隔（分钟）")
    private Integer intervalSeconds;
    /*** 重复次数-仅需要按周期多次调度时有效 **/
    @ApiModelProperty("重复次数")
    private Integer repeatCount;
    /*** 执行定时任务cron表达式 **/
    @ApiModelProperty("执行定时任务cron表达式")
    private String cron;
    /*** 任务结束时间 **/
    @ApiModelProperty("任务结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    /*** 是否启用 **/
    @ApiModelProperty("是否启用")
    private Boolean enable;
}
