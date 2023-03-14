package org.gecko.reformer.config;

import lombok.Data;
import org.gecko.reformer.util.SpringUtils;

import java.io.File;

/**
 * fink cdc任务基础配置
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-02
 **/
@Data
public class ReformerFlinkCdcBaseProperties {

    /*** 作业任务名，不能重复 **/
    private String jobName;
    /*** 将checkpoint元数据写入到的路径 **/
    private String checkPointDir = "flink/save-point";
    /*** 状态检查点间隔，单位为毫秒(默认5秒) **/
    private long checkpointTime = 5000;
    /*** check point超时时间，单位为毫秒(默认10秒) **/
    private long checkpointTimeout = 10000;

    public String getCheckPointDir() {
        final String userHome = System.getProperty("user.home");
        return userHome + File.separator + checkPointDir + File.separator + appName();
    }

    /**
     * 获取应用名称
     *
     * @param
     * @return java.lang.String
     * @throws
     * @author LZC
     * @date 2023-03-05
     * @version 1.1.2
     **/
    private String appName() {
        return SpringUtils.getProperty("spring.application.name", String.class, "DEFAULT");
    }
}
