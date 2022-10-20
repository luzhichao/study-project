package org.gecko.reformer.job;

import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.constant.JobConstants;
import org.gecko.reformer.exception.CustomException;
import org.gecko.reformer.xxljob.ReformerXxlJob;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author LZC
 * @version 1.0.0
 * @date 2022-07-05
 **/
@Slf4j
@Component
public class TestJob extends ReformerXxlJob {
    @Override
    public void run() {
        log.info("---------------- testJob run");
        JobConstants.JOB_EXCEPTION_INDEX++;
        if (JobConstants.JOB_EXCEPTION_INDEX % 2 == 0) {
            throw new CustomException("testJob error");
        }
        log.info("---------------- testJob end");
    }
}
