package org.gecko.reformer.controller;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.gecko.reformer.annotation.WebController;
import org.gecko.reformer.engine.ScheduleJobEngine;
import org.gecko.reformer.pojo.CronScheduleJobManyDTO;
import org.gecko.reformer.job.TestJob;
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
@Api(tags = "test")
@WebController
@RequestMapping("/trigger/api/")
public class TestController {

    @Autowired
    private TestJob job;

    @SneakyThrows
    @ApiOperation("testSimpleJobCreate")
    @PostMapping("/testSimpleJobCreate")
    public Result<String> testSimpleJobCreate(@RequestBody JobDTO dto) {
        final CronScheduleJobManyDTO jobDTO = BeanUtil.copyProperties(dto, CronScheduleJobManyDTO.class);
        ScheduleJobEngine.createCronManyJob(jobDTO, job);
        return Result.success();
    }

    @SneakyThrows
    @ApiOperation("testCronSingleJobCreate")
    @PostMapping("/testCronSingleJobCreate")
    public Result<String> testCronSingleJobCreate(@RequestBody JobDTO dto) {
        final CronScheduleJobManyDTO jobDTO = BeanUtil.copyProperties(dto, CronScheduleJobManyDTO.class);
        ScheduleJobEngine.createCronManyJob(jobDTO, job);
        return Result.success();
    }

    @SneakyThrows
    @ApiOperation("testCronManyJonCreate")
    @PostMapping("/testCronManyJonCreate")
    public Result<String> testCronManyJonCreate(@RequestBody JobDTO dto) {
        final CronScheduleJobManyDTO jobDTO = BeanUtil.copyProperties(dto, CronScheduleJobManyDTO.class);
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
