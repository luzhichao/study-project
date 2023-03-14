package org.gecko.reformer.transform;

import com.ververica.cdc.debezium.DebeziumDeserializationSchema;
import io.debezium.data.Envelope;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.util.Collector;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-07
 **/
public class CustomDebeziumDeserializer implements DebeziumDeserializationSchema<String> {
    @Override
    public void deserialize(SourceRecord sourceRecord, Collector<String> collector) throws Exception {
        Map<String, Object> parsedObject = new HashMap<>();

        // 注意，SourceRecord中并没有获取操作类型的方法。获取操作类型需要这么写
        Envelope.Operation operation = Envelope.operationFor(sourceRecord);
        parsedObject.put("operation", operation.toString());

        // topic返回的内容为：mysql_binlog_source.dbName.tableName，即数据源.数据库名.表名
        // 按照业务使用要求解析即可
        if (null != sourceRecord.topic()) {
            String[] splitTopic = sourceRecord.topic().split("\\.");
            if (splitTopic.length == 3) {
                parsedObject.put("database", splitTopic[1]);
                parsedObject.put("table", splitTopic[2]);
            }
        }

        // value返回sourceRecord中携带的数据，它是一个Struct类型
        // Struct的类型为org.apache.kafka.connect.data.Struct
        Struct value = (Struct) sourceRecord.value();
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
            parsedObject.put("before", beforeMap);
        }

        if (null != after) {
            Map<String, Object> afterMap = new HashMap<>();
            Schema afterSchema = after.schema();
            for (Field field : afterSchema.fields()) {
                afterMap.put(field.name(), after.get(field));
            }

            parsedObject.put("after", afterMap);
        }

        // 调用collector的collect方法，将转换后的数据发往下游
        collector.collect(parsedObject.toString());
    }

    @Override
    public TypeInformation<String> getProducedType() {
        return BasicTypeInfo.STRING_TYPE_INFO;
    }
}
