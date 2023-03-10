package org.gecko.reformer.example;

import com.google.common.collect.Sets;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.gecko.reformer.constant.MysqlStartupMode;
import org.gecko.reformer.dto.ReformerFlinkMySqlDTO;
import org.gecko.reformer.util.FlinkUtils;

public class MySqlSourceExample {
    public static void main(String[] args) throws Exception {
        // 外部配置
        //ParameterTool parameterTool = ParameterTool.fromPropertiesFile("/home/config.properties");
        // hdfs://192.168.11.59:9000/user/reformer
        ReformerFlinkMySqlDTO dto = new ReformerFlinkMySqlDTO();
        dto.setHostname("192.168.11.100");
        dto.setPort(7006);
        dto.setDatabases(Sets.newHashSet("lzc_test"));
        dto.setTables(Sets.newHashSet("^lzc_test.t_vehicle_alarm_[0-9]{4}(_)(1[0-2]|0?[1-9])$"));
        dto.setUsername("canal");
        dto.setPassword("reformer");
        dto.setConnectionPoolSize(10);
        dto.setHeartbeatInterval(30);
        dto.setConnectTimeout(30);
        dto.setConnectMaxRetries(3);
        dto.setServerTimeZone("Asia/Shanghai");
        dto.setOptionMode(MysqlStartupMode.LATEST_OFFSET);
        dto.setJobName("test-mysql-job");
        dto.setCheckPointDir("/Users/luzhichao/Downloads/flink/save-point/");
        dto.setCheckpointTime(5000);
        dto.setCheckpointTimeout(10000);

        DataStreamSource<String> source = FlinkUtils.getMysqlSource(dto);

        source.print().setParallelism(1);
        final StreamExecutionEnvironment env = source.getExecutionEnvironment();

        env.execute();
    }
}
