package com.reformer.flink;

import com.ververica.cdc.connectors.mysql.table.StartupOptions;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.datastream.DataStreamSink;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import com.ververica.cdc.debezium.JsonDebeziumDeserializationSchema;
import com.ververica.cdc.connectors.mysql.source.MySqlSource;

public class MySqlSourceExample {
    public static void main(String[] args) throws Exception {
        MySqlSource<String> mySqlSource = MySqlSource.<String>builder()
                .hostname("192.168.11.100")
                .port(7006)
                .databaseList("dev_rf_transport")
                .tableList("dev_rf_transport.t_disposal_description")
                .username("root")
                .password("05NG6j")
                .deserializer(new JsonDebeziumDeserializationSchema())
                // 不支持
                //.startupOptions(StartupOptions.earliest())
                // 执行初始快照，并继续读取最新的binlog
                .startupOptions(StartupOptions.initial())
                // 读取最新的binlog
                //.startupOptions(StartupOptions.latest())
                // 不支持
                //.startupOptions(StartupOptions.timestamp(1675735994502L))
                .build();

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // enable checkpoint
        env.enableCheckpointing(3000);

        final DataStreamSink<String> mySQL_source = env.fromSource(mySqlSource, WatermarkStrategy.noWatermarks(), "MySQL Source")
                //.setParallelism(4)
                .print().setParallelism(1);

        env.execute("Print MySQL Snapshot + Binlog");
    }
}
