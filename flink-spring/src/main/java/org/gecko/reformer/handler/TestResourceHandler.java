package org.gecko.reformer.handler;

import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.annotation.FlinkHandler;
import org.gecko.reformer.domain.SourceMongoDO;
import org.gecko.reformer.domain.UpdateMongoDTO;
import org.gecko.reformer.model.TestResourceModel;

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
    public void insert(String id, TestResourceModel doc) {
        log.info("insert===id==={},===doc==={}", id, doc);
    }

    @Override
    public void update(String id, UpdateMongoDTO dto, TestResourceModel doc) {
        log.info("update===id==={}", id);
        log.info("update===dto==={}", dto);
        log.info("update===doc==={}", doc);
        final SourceMongoDO source = getSource();
        log.info("source==={}", source);
    }

    @Override
    public void delete(String id) {
        log.info("delete===id==={}", id);
    }
}
