package org.gecko.reformer.schema;

import com.ververica.cdc.debezium.DebeziumDeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.util.Collector;
import org.apache.kafka.connect.source.SourceRecord;
import org.gecko.reformer.model.TestModel;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-11
 **/
public class TestSchema implements DebeziumDeserializationSchema<TestModel> {

    @Override
    public void deserialize(SourceRecord record, Collector<TestModel> out) throws Exception {

    }

    @Override
    public TypeInformation<TestModel> getProducedType() {
        return TypeInformation.of(TestModel.class);
    }
}
