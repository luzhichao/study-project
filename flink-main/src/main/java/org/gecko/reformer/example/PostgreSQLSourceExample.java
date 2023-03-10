package org.gecko.reformer.example;

import com.google.common.collect.Sets;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.gecko.reformer.constant.PgPluginEnum;
import org.gecko.reformer.dto.ReformerFlinkPostgreSqlDTO;
import org.gecko.reformer.util.FlinkUtils;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-07
 **/
public class PostgreSQLSourceExample {

    public static void main(String[] args) throws Exception {

        ReformerFlinkPostgreSqlDTO dto = new ReformerFlinkPostgreSqlDTO();
        dto.setHostname("192.168.11.100");
        dto.setPort(5555);
        dto.setDatabase("dataAnalysis");
        dto.setSchemas(Sets.newHashSet("public"));
        dto.setTables(Sets.newHashSet("public.lzc_test"));
        dto.setUsername("postgres");
        dto.setPassword("postgres");
        dto.setPluginName(PgPluginEnum.WAL_2_JSON);
        dto.setSlotName("flink");
        dto.setJobName("test-pg-job");
        dto.setCheckPointDir("/Users/luzhichao/Downloads/flink/save-point/");
        dto.setCheckpointTime(5000);
        dto.setCheckpointTimeout(10000);

        DataStreamSource<String> source = FlinkUtils.getPgSource(dto);

        source.print().setParallelism(1);
        final StreamExecutionEnvironment env = source.getExecutionEnvironment();

        env.execute();
    }
}
