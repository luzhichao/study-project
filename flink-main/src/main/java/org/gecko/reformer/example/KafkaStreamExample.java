package org.gecko.reformer.example;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.google.common.collect.Sets;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.PipelineOptions;
import org.apache.flink.configuration.PipelineOptionsInternal;
import org.apache.flink.contrib.streaming.state.EmbeddedRocksDBStateBackend;
import org.apache.flink.runtime.state.StateBackend;
import org.apache.flink.runtime.state.storage.FileSystemCheckpointStorage;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.gecko.reformer.constant.FlinkConstants;
import org.gecko.reformer.constant.KafkaStartupMode;
import org.gecko.reformer.dto.ReformerFlinkKafkaDTO;
import org.gecko.reformer.util.FlinkUtils;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2022-12-29
 **/
public class KafkaStreamExample {

    public static void main(String[] args) throws Exception {

        final ReformerFlinkKafkaDTO dto = new ReformerFlinkKafkaDTO();
        dto.setJobName("test-kafka-job");
        dto.setCheckPointDir("/Users/luzhichao/Downloads/flink/save-point/");
        dto.setCheckpointTime(5000);
        dto.setCheckpointTimeout(10000);
        dto.setBootstrapServers("192.168.11.103:9092,192.168.11.104:9092,192.168.11.105:9092");
        dto.setGroupId("flink-kafka-group");
        dto.setTopics(Sets.newHashSet("dev_test-kafka"/*,"elink-upstream-gps-topic"*/));
        dto.setClientIdPrefix("test");
        dto.setScanPartition(100);
        dto.setOptionMode(KafkaStartupMode.INITIAL);

        final DataStreamSource<String> kafkaSource = FlinkUtils.getKafkaSource(dto);
        kafkaSource.print().setParallelism(1);

        final StreamExecutionEnvironment evn = kafkaSource.getExecutionEnvironment();
        evn.execute();


        //String regex = "regex";
        //final Pattern pattern = Pattern.compile(regex);
        //
        //KafkaSource<String> source = KafkaSource.<String>builder()
        //        .setBootstrapServers("192.168.11.103:9092,192.168.11.104:9092,192.168.11.105:9092")
        //        .setGroupId("Kafka-group")
        //        .setTopics("dev_test-kafka","elink-upstream-gps-topic")
        //        //.setTopicPattern(pattern)
        //        // 设置KafkaSource的客户端id前缀
        //        .setClientIdPrefix("test")
        //        // 指定KafkaSource从哪偏移开始消费
        //        .setStartingOffsets(OffsetsInitializer.earliest())
        //        // 设置以批处理模式运行当所有分区达到指定偏移量时停止
        //        //.setBounded(OffsetsInitializer.latest())
        //        //// 设置以流模式运行当所有分区达到指定的偏移量时停止
        //        //.setUnbounded(OffsetsInitializer.latest())
        //        // 设置KafkaSource的ConsumerRecord的deserializer
        //        //.setDeserializer(KafkaRecordDeserializationSchema.valueOnly(new SimpleStringSchema()))
        //        .setValueOnlyDeserializer(new SimpleStringSchema())
        //        // 辅助参数：
        //        // partition.discovery.interval.ms定义 Kafka 源发现新分区的时间间隔毫秒
        //        //.setProperties(new Properties())
        //        .setProperty("partition.discovery.interval.ms", "100")
        //
        //        .build();
        //
        //final StreamExecutionEnvironment env = getEnv();
        //
        //final DataStreamSource<String> kafka_source = env.fromSource(source, WatermarkStrategy.noWatermarks(), "Kafka Source");
        //
        //kafka_source.print().setParallelism(1);
        //
        //env.execute();
    }

    private static StreamExecutionEnvironment getEnv(){
        String jobName = "test-kafka-job";
        String checkPointDir = "/Users/luzhichao/Downloads/flink/save-point/" + jobName;
        final String jobId = DigestUtil.md5Hex(jobName);

        final String firstDir = "/Users/luzhichao/Downloads/flink/save-point/test-kafka-job/6fed92c4bf53a8374bf912ecc0c7f330/chk-7";
        String jobCheckPointDir = StrUtil.format(FlinkConstants.CHECK_POINT_SYS_FILE_AGREEMENT_TEMPLATE, checkPointDir);

        Configuration configuration = new Configuration();
        // The expected format is [0-9a-fA-F]{32}
        configuration.setString(PipelineOptions.NAME, jobName);
        configuration.setString(PipelineOptionsInternal.PIPELINE_FIXED_JOB_ID, jobId);
        if (StrUtil.isNotBlank(firstDir)) {
            configuration.setString(FlinkConstants.EXECUTION_SAVE_POINT_COMMAND, firstDir);
        }

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment(configuration);
        StateBackend backend = new EmbeddedRocksDBStateBackend(true);
        env.setStateBackend(backend);
        // enable checkpoint
        env.enableCheckpointing(3000);
        final FileSystemCheckpointStorage storage = new FileSystemCheckpointStorage(jobCheckPointDir);
        CheckpointConfig checkpointConfig = env.getCheckpointConfig();
        checkpointConfig.setCheckpointStorage(storage);
        checkpointConfig.setCheckpointTimeout(3000);

        return env;
    }

}
