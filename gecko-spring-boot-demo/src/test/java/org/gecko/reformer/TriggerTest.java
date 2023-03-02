package org.gecko.reformer;

import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.engine.ScheduleJobEngine;
import org.gecko.reformer.job.job.TestTriggerJob;
import org.gecko.reformer.pojo.CronScheduleJobManyDO;
import org.gecko.reformer.pojo.CronScheduleJobSingleDO;
import org.gecko.reformer.pojo.SimpleScheduleJobDO;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 触发任务测试
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-09-08
 **/
@Slf4j
@SpringBootTest
public class TriggerTest {

    @Resource
    private TestTriggerJob job;

    @Test
    public void testSimpleScheduleJob() {
        //   "cron": "",
        //  "enable": true,
        //  "end_time": "",
        //  "group": "",
        //  "interval_seconds": 0,
        //  "name": "",
        //  "param_data": {},
        //  "priority": 0,
        //  "repeat_count": 0,
        //  "start_time": ""
        final SimpleScheduleJobDO dto = new SimpleScheduleJobDO();
        ScheduleJobEngine.createSimpleJob(dto, job);
    }

    @Test
    public void testCronSingleJobCreate() {
        final CronScheduleJobSingleDO dto = new CronScheduleJobSingleDO();
        ScheduleJobEngine.createCronSingleJob(dto, job);
    }

    @Test
    public void testCronManyJobCreate() {
        //  "cron": "* * * * * ?",
        //  "enable": true,
        //  "end_time": "",
        //  "group": "test-job-cron-many",
        //  "interval_seconds": 1,
        //  "name": "test-job-cron-many",
        //  "param_data": {"abc":"123"},
        //  "priority": 0,
        //  "repeat_count": 2,
        //  "start_time": ""
        final CronScheduleJobManyDO dto = new CronScheduleJobManyDO();
        ScheduleJobEngine.createCronManyJob(dto, job);
    }

    @Test
    public void testPauseJob() {
        String group = "";
        ScheduleJobEngine.pauseJob(group);
    }

    @Test
    public void testResumeJob() {
        String group = "";
        ScheduleJobEngine.resumeJob(group);
    }

    @Test
    public void testDeleteJob() {
        String group = "";
        ScheduleJobEngine.deleteJob(group);
    }
}
