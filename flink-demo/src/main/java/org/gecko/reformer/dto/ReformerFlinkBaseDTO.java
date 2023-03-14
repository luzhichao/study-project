package org.gecko.reformer.dto;

import cn.hutool.crypto.digest.DigestUtil;
import lombok.Data;

import java.io.File;

/**
 * fink任务基础DTO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-02
 **/
@Data
public class ReformerFlinkBaseDTO {

    /*** 作业任务名，不能重复 **/
    private String jobName;
    /*** 将checkpoint元数据写入到的路径flink/save-point **/
    private String checkPointDir;
    /*** 状态检查点间隔，单位为毫秒(默认5秒) **/
    private long checkpointTime = 5000;
    /*** check point超时时间，单位为毫秒(默认10秒) **/
    private long checkpointTimeout = 10000;

    /**
     * 获取任务ID
     *
     * @param
     * @return java.lang.String
     * @throws
     * @author LZC
     * @date 2023-03-08
     * @version 1.1.2
     **/
    public String getJobId() {
        return DigestUtil.md5Hex(jobName);
    }

    public String getCheckPointDir() {
        return checkPointDir + File.separator + jobName;
    }
}
