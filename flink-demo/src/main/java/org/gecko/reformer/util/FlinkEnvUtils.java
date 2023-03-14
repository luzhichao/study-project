package org.gecko.reformer.util;

import cn.hutool.core.util.StrUtil;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.PipelineOptions;
import org.apache.flink.configuration.PipelineOptionsInternal;
import org.apache.flink.contrib.streaming.state.EmbeddedRocksDBStateBackend;
import org.apache.flink.runtime.state.StateBackend;
import org.apache.flink.runtime.state.storage.FileSystemCheckpointStorage;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.gecko.reformer.constant.FlinkConstants;
import org.gecko.reformer.dto.ReformerFlinkBaseDTO;

import java.util.List;

/**
 * flink 执行环境工具类
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-09
 **/
final class FlinkEnvUtils {
    private FlinkEnvUtils() {
    }

    /**
     * 构建流试数据执行环境，通过文件保存检查点
     *
     * @param dto
     * @return org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
     * @throws
     * @author LZC
     * @date 2023-03-09
     * @version 1.1.2
     **/
    protected static StreamExecutionEnvironment getStreamExecute(ReformerFlinkBaseDTO dto) {
        String jobName = dto.getJobName();
        String checkPointDir = dto.getCheckPointDir();
        final String jobId = dto.getJobId();

        List<String> allChkDir = FlinkFileUtils.searchCheckPointDir(checkPointDir, jobId, FlinkConstants.CHECK_POINT_DIR_PREFIX);
        final String firstDir = FlinkFileUtils.getMaxDir(allChkDir);
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
        env.enableCheckpointing(dto.getCheckpointTime());
        final FileSystemCheckpointStorage storage = new FileSystemCheckpointStorage(jobCheckPointDir);
        CheckpointConfig checkpointConfig = env.getCheckpointConfig();
        checkpointConfig.setCheckpointStorage(storage);
        checkpointConfig.setCheckpointTimeout(dto.getCheckpointTimeout());

        // 清理
        FlinkFileUtils.delPastDir(allChkDir);
        return env;
    }

}
