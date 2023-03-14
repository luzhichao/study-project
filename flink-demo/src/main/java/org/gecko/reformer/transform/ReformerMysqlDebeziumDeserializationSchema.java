package org.gecko.reformer.transform;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.ververica.cdc.debezium.DebeziumDeserializationSchema;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.util.Collector;
import org.apache.kafka.connect.json.JsonConverter;
import org.apache.kafka.connect.json.JsonConverterConfig;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.storage.ConverterConfig;
import org.apache.kafka.connect.storage.ConverterType;
import org.gecko.reformer.domain.DataChangeDO;

import java.util.HashMap;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-06
 **/
public class ReformerMysqlDebeziumDeserializationSchema implements DebeziumDeserializationSchema<DataChangeDO> {

    private transient JsonConverter jsonConverter;

    @Override
    public void deserialize(SourceRecord record, Collector out) throws Exception {
        if (jsonConverter == null) {
            initializeJsonConverter();
        }

        byte[] bytes = jsonConverter.fromConnectData(record.topic(), record.valueSchema(), record.value());
        final String str = StrUtil.str(bytes, CharsetUtil.UTF_8);

        DataChangeDO dto = JSONUtil.toBean(str, DataChangeDO.class);

        // 调用collect方法，将转换后的数据发往下游
        out.collect(dto);
    }

    @Override
    public TypeInformation getProducedType() {
        return BasicTypeInfo.STRING_TYPE_INFO;
    }

    private void initializeJsonConverter() {
        jsonConverter = new JsonConverter();
        final HashMap<String, Object> configs = new HashMap<>(2);
        configs.put(ConverterConfig.TYPE_CONFIG, ConverterType.VALUE.getName());
        configs.put(JsonConverterConfig.SCHEMAS_ENABLE_CONFIG, false);
        jsonConverter.configure(configs);
    }
}
