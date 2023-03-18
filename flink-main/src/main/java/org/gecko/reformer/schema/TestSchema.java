package org.gecko.reformer.schema;

import io.debezium.data.Envelope;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.util.Collector;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.gecko.reformer.model.TestModel;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义解析框架
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-11
 **/
@Slf4j
public class TestSchema extends BaseReformerDeserializationSchema<TestModel> {
    private static final long serialVersionUID = 1L;

    @Override
    public void deserialize(SourceRecord record, Collector<TestModel> out) throws Exception {
        log.info("TestSchema#deserialize==={}", record);
        TestModel tm = new TestModel();

        // 注意，SourceRecord中并没有获取操作类型的方法。获取操作类型需要这么写
        Envelope.Operation operation = Envelope.operationFor(record);
        tm.setOperation(operation.toString());

        // topic返回的内容为：mysql_binlog_source.dbName.tableName，即数据源.数据库名.表名
        // 按照业务使用要求解析即可
        if (null != record.topic()) {
            String[] splitTopic = record.topic().split("\\.");
            if (splitTopic.length == 3) {
                tm.setDatabase(splitTopic[1]);
                tm.setTable(splitTopic[2]);
            }
        }

        // value返回sourceRecord中携带的数据，它是一个Struct类型
        // Struct的类型为org.apache.kafka.connect.data.Struct
        Struct value = (Struct) record.value();
        // 变更前后的数据位于value这个Struct中，名称分别为before和after
        Struct before = value.getStruct("before");
        Struct after = value.getStruct("after");

        // 对于新增或删除的数据，before和after可能不存在，需要做null检查
        if (null != before) {
            Map<String, Object> beforeMap = new HashMap<>();
            // 获取Struct中包含所有字段名可以使用struct.schema().fields()方法，遍历即可
            Schema beforeSchema = before.schema();
            for (Field field : beforeSchema.fields()) {
                beforeMap.put(field.name(), before.get(field));
            }
            tm.setBefore(beforeMap);
        }

        if (null != after) {
            Map<String, Object> afterMap = new HashMap<>();
            Schema afterSchema = after.schema();
            for (Field field : afterSchema.fields()) {
                afterMap.put(field.name(), after.get(field));
            }
            tm.setAfter(afterMap);
        }
        // 调用collector的collect方法，将转换后的数据发往下游
        out.collect(tm);
    }
}
