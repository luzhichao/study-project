package org.gecko.reformer.canal_client.handler;

import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.annotation.CanalTable;
import org.gecko.reformer.canal_client.model.UserModel;
import org.gecko.reformer.handler.EntryHandler;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@CanalTable("test_two")
public class UserHandler implements EntryHandler<UserModel> {

    @Override
    public void insert(UserModel user) {
        log.info("实体Handler 新增数据 {}", user);
    }

    @Override
    public void update(UserModel before, UserModel after) {
        log.info("实体Handler 更新数据");
        log.info("实体Handler 更新前数据 {} ", before);
        log.info("实体Handler 更新后数据 {}", after);
    }

    @Override
    public void delete(UserModel user) {
        log.info("实体Handler 删除数据  {}", user);
    }
}
