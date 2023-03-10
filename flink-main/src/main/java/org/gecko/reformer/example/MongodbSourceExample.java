package org.gecko.reformer.example;

import com.ververica.cdc.connectors.mongodb.MongoDBSource;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.gecko.reformer.constant.OpErrorEnum;
import org.gecko.reformer.dto.ReformerFlinkMongoDTO;
import org.gecko.reformer.transform.MongodbDebeziumDeserializationSchema;
import org.gecko.reformer.util.FlinkUtils;

import java.util.HashSet;

public class MongodbSourceExample {

    public static void main(String[] args) throws Exception {
        // hdfs://192.168.11.59:9000/user/reformer
        ReformerFlinkMongoDTO dto = new ReformerFlinkMongoDTO();
        dto.setJobName("test-mongodb-job");
        dto.setHosts("192.168.11.100:7004");
        dto.setUsername("reformer");
        dto.setPassword("123456");
        final HashSet databases = new HashSet<>();
        databases.add("reformer");
        dto.setDatabases(databases);
        final HashSet<String> collects = new HashSet<>();
        collects.add("reformer.rf_resource");
        dto.setCollects(collects);
        dto.setErrorsTolerance(OpErrorEnum.ALL);
        dto.setBatchSize(1000);
        dto.setPollMaxBatchSize(1000);
        dto.setAwaitTime(1500);
        dto.setHeartbeatInterval(30000);
        dto.setConnectionOptions("authSource=reformer");
        dto.setCopyExisting(true);
        dto.setCheckPointDir("/Users/luzhichao/Downloads/flink/save-point/");
        dto.setCheckpointTime(5000);
        dto.setCheckpointTimeout(10000);

        DataStreamSource<String> source = FlinkUtils.getMgSource(dto);
        source.print().setParallelism(1);
        final StreamExecutionEnvironment env = source.getExecutionEnvironment();
        env.execute();
    }

    public static void test() throws Exception {
        //new MongoDBConnectorDeserializationSchema(RowType.of(),new MetadataConverter[0],new RowTypeInfo(), ZoneId.systemDefault())
        //ServerApiVersion
        //JsonObjectCodecProvider
        SourceFunction<String> sourceFunction = MongoDBSource.<String>builder()
                .hosts("192.168.11.100:7004")
                .username("root")
                .password("123456")
                .databaseList("reformer")
                .collectionList("reformer.rf_resource")
                .pollAwaitTimeMillis(3000)
                //.copyExisting(false)
                //.connectionOptions("replicaSet=reformer&connectTimeoutMS=300000")
                .deserializer(new MongodbDebeziumDeserializationSchema())
                .build();

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.addSource(sourceFunction)
                .print().setParallelism(1);

        env.execute();
    }
}
