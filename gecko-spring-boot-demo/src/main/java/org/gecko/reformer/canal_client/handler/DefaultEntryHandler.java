package org.gecko.reformer.canal_client.handler;

import lombok.extern.slf4j.Slf4j;
import org.gecko.reformer.annotation.CanalTable;
import org.gecko.reformer.constant.CanalConstants;
import org.gecko.reformer.context.CanalContext;
import org.gecko.reformer.handler.EntryHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 获取map对象
 *
 * @author yang peng
 * @date 2019/4/1915:19
 */
@Slf4j
@Component
@CanalTable(CanalConstants.ALL)
public class DefaultEntryHandler implements EntryHandler<Map<String, String>> {

    @Override
    public void insert(Map<String, String> map) {
        String table = CanalContext.getModel().getTable();
        log.info("Map Handler 数据库表：{} 增加数据 {}", table, map);
    }

    @Override
    public void update(Map<String, String> before, Map<String, String> after) {
        String table = CanalContext.getModel().getTable();
        log.info("Map Handler 数据库表：{} 修改数据", table);
        log.info("修改前 {}", before);
        log.info("修改后 {}", after);
    }

    @Override
    public void delete(Map<String, String> map) {
        String table = CanalContext.getModel().getTable();
        log.info("Map Handler 数据库表：{} 删除数据 {}", table, map);
    }
}
