package org.gecko.reformer.flink.handler;

import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.annotation.FlinkHandler;
import org.gecko.reformer.domain.SourceMongoDO;
import org.gecko.reformer.domain.UpdateMongoDTO;
import org.gecko.reformer.flink.model.TestResourceModel;
import org.gecko.reformer.handler.AbsMongoHandler;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-10
 **/
@Slf4j
@FlinkHandler(jobName = "test-mongo-job", modelClass = TestResourceModel.class)
public class TestResourceHandler extends AbsMongoHandler<TestResourceModel> {


    @Override
    public void insert(String id, TestResourceModel after) {
        log.info("insert===id==={},===after==={}", id, after);
    }

    @Override
    public void update(String id, UpdateMongoDTO dto, TestResourceModel after) {
        log.info("update===id==={}", id);
        log.info("update===dto==={}", dto);
        log.info("update===after==={}", after);
        final SourceMongoDO source = getSource();
        log.info("source==={}", source);
    }

    @Override
    public void delete(String id) {
        log.info("delete===id==={}", id);
    }
}
