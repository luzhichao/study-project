package org.gecko.reformer.flink.handler;

import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.annotation.FlinkHandler;
import org.gecko.reformer.domain.SourceSqlDO;
import org.gecko.reformer.flink.model.TestAlarmModel;
import org.gecko.reformer.handler.AbsSqlHandler;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-09
 **/
@Slf4j
@FlinkHandler(jobName = "test-mysql-job", modelClass = TestAlarmModel.class)
public class TestAlarmHandler extends AbsSqlHandler<TestAlarmModel> {

    @Override
    public void insert(TestAlarmModel t) {
        log.info("====insert===={}", t);
    }

    @Override
    public void update(TestAlarmModel before, TestAlarmModel after) {
        log.info("====update==before=={}", before);
        log.info("====update==after=={}", after);
        final SourceSqlDO source = getSource();
        log.info("更新元数据=={}", source);
    }

    @Override
    public void delete(TestAlarmModel t) {
        log.info("====delete===={}", t);
    }
}
