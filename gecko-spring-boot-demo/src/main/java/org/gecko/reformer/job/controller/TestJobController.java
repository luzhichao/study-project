package org.gecko.reformer.job.controller;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.gecko.reformer.annotation.WebController;
import org.gecko.reformer.engine.ScheduleJobEngine;
import org.gecko.reformer.pojo.CronScheduleJobManyDO;
import org.gecko.reformer.job.job.TestJob;
import org.gecko.reformer.pojo.CronScheduleJobSingleDO;
import org.gecko.reformer.pojo.SimpleScheduleJobDO;
import org.gecko.reformer.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * test
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-09-11
 **/
@Api(tags = "TestJob")
@WebController
@RequestMapping("/trigger/api/")
public class TestJobController {

    @Autowired
    private TestJob job;

    @SneakyThrows
    @ApiOperation("testSimpleJobCreate")
    @PostMapping("/testSimpleJobCreate")
    public Result<String> testSimpleJobCreate(@RequestBody JobDTO dto) {
        final SimpleScheduleJobDO jobDTO = BeanUtil.copyProperties(dto, SimpleScheduleJobDO.class);
        ScheduleJobEngine.createSimpleJob(jobDTO, job);
        return Result.success();
    }

    @SneakyThrows
    @ApiOperation("testCronSingleJobCreate")
    @PostMapping("/testCronSingleJobCreate")
    public Result<String> testCronSingleJobCreate(@RequestBody JobDTO dto) {
        final CronScheduleJobSingleDO jobDTO = BeanUtil.copyProperties(dto, CronScheduleJobSingleDO.class);
        ScheduleJobEngine.createCronSingleJob(jobDTO, job);
        return Result.success();
    }

    @SneakyThrows
    @ApiOperation("testCronManyJobCreate")
    @PostMapping("/testCronManyJobCreate")
    public Result<String> testCronManyJobCreate(@RequestBody JobDTO dto) {
        final CronScheduleJobManyDO jobDTO = BeanUtil.copyProperties(dto, CronScheduleJobManyDO.class);
        ScheduleJobEngine.createCronManyJob(jobDTO, job);
        return Result.success();
    }

    @SneakyThrows
    @ApiOperation("testPauseJob")
    @PostMapping("/testPauseJob")
    public Result<String> testPauseJob(@RequestBody String group) {
        ScheduleJobEngine.pauseJob(group);
        return Result.success();
    }

    @SneakyThrows
    @ApiOperation("testResumeJob")
    @PostMapping("/testResumeJob")
    public Result<String> testResumeJob(@RequestBody String group) {
        ScheduleJobEngine.resumeJob(group);
        return Result.success();
    }

    @SneakyThrows
    @ApiOperation("testDeleteJob")
    @PostMapping("/testDeleteJob")
    public Result<String> testDelete(@RequestBody String group) {
        ScheduleJobEngine.deleteJob(group);
        return Result.success();
    }

}
