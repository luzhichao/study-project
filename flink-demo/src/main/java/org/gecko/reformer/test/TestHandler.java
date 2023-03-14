package org.gecko.reformer.test;

import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.annotation.FlinkHandler;
import org.gecko.reformer.domain.SourceDO;
import org.gecko.reformer.handler.AbsSqlHandler;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-09
 **/
@Slf4j
@FlinkHandler(jobName = "test-mysql-job", modelClass = TestModel.class)
public class TestHandler extends AbsSqlHandler<TestModel> {
    @Override
    public void insert(TestModel t) {
        log.info("====insert===={}", t);
    }

    @Override
    public void update(TestModel before, TestModel after) {
        log.info("====update==before=={}", before);
        log.info("====update==after=={}", after);
        final SourceDO source = getSource();
        log.info("更新元数据=={}", source);
    }

    @Override
    public void delete(TestModel t) {
        log.info("====delete===={}", t);
    }
}
