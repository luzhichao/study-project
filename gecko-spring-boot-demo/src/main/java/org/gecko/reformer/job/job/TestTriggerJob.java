package org.gecko.reformer.job.job;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.quartz.ReformerScheduleJob;
import org.quartz.JobExecutionContext;
import org.quartz.TriggerKey;
import org.springframework.stereotype.Component;

/**
 * TestJob
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-09-08
 **/
@Slf4j
@Component
public class TestTriggerJob implements ReformerScheduleJob {

    @Override
    public void run(JobExecutionContext context, Object param) {
        final TriggerKey key = context.getTrigger().getKey();
        log.info("=====执行TestJob业务任务了===group=【{}】===name=【{}】=====参数=====【{}】", key.getGroup(), key.getName(), JSONUtil.toJsonStr(param));
    }
}

