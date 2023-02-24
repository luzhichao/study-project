package com.reformer.trigger.job;

import cn.hutool.json.JSONUtil;
import com.reformer.dictionary.api.dto.ItemDTO;
import com.reformer.job.quartz.ReformerScheduleJob;
import com.reformer.trigger.feign.FeignUtils;
import com.reformer.trigger.service.ITestService;
import com.reformer.utils.SpringBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.TriggerKey;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * TestJob
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-09-08
 **/
@Slf4j
@Component
public class TestJob implements ReformerScheduleJob {

    @Override
    public void run(JobExecutionContext context, Object param) {
        ITestService testService = SpringBeanUtils.getBean(ITestService.class);
        final TriggerKey key = context.getTrigger().getKey();
        log.info("=====执行TestJob业务任务了===group=【{}】===name=【{}】=====参数=====【{}】", key.getGroup(), key.getName(), JSONUtil.toJsonStr(param));
        testService.test();

        FeignUtils feignUtils = SpringBeanUtils.getBean(FeignUtils.class);
        final List<ItemDTO> items = feignUtils.queryItemListByTypeCode("CONTROL_COMPONENTS");
        log.info("=====items====={}", items);

    }
}

