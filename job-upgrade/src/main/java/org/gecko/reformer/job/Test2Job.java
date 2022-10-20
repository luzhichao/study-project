package org.gecko.reformer.job;

import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.constant.JobConstants;
import org.gecko.reformer.exception.CustomException;
import org.gecko.reformer.annotations.ReformerJob;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-07-06
 **/
@Slf4j
@Component
public class Test2Job {

    @ReformerJob("test2Job")
    public void execute() {
        log.info("---------------- test2Job run");
        JobConstants.JOB_EXCEPTION_INDEX++;
        if (JobConstants.JOB_EXCEPTION_INDEX % 3 == 0) {
            throw new CustomException("test2Job error");
        }
        log.info("---------------- test2Job end");
    }


}
