package com.reformer.flink;

import com.ververica.cdc.connectors.mysql.source.MySqlSource;
import com.ververica.cdc.debezium.JsonDebeziumDeserializationSchema;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @Author: yingzi
 * @Date: 2022/7/17 10:00
 * @Version 1.0
 */
public class OdsVehicleRealLocationApp {

    public static void main(String[] args) throws Exception {

        //1、获取执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //2、通过FlinkCDC构建SourceFunction并读取数据
        ParameterTool parameterTool = ParameterTool.fromPropertiesFile("/Users/luzhichao/workspace/GitHub/study-project/flink-demo/src/main/resources/config.properties");
        String host = parameterTool.get("source.dataSource.host");
        int port = parameterTool.getInt("source.dataSource.port");
        String username = parameterTool.get("source.dataSource.username");
        String password = parameterTool.get("source.dataSource.password");
        String databaseList = parameterTool.get("source.dataSource.databaseList");
        String tableList = parameterTool.get("source.dataSource.tableList");
        MySqlSource<String> mySqlSource = MySqlSource.<String>builder()
                .hostname(host)
                .port(port)
                .username(username)
                .password(password)
                .databaseList(databaseList)
                .tableList(tableList)
                .deserializer(new JsonDebeziumDeserializationSchema())
                // 先读取指定表的快照，再继续读取最新的binlog
                //.startupOptions(StartupOptions.earliest())
                .build();

        env.fromSource(mySqlSource, WatermarkStrategy.noWatermarks(), "MySQL Source")
                .print()
                .setParallelism(1);


        //4、启动任务
        env.execute("ods_car_info");
    }

}
