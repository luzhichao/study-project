package org.gecko.reformer.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * fink cdc配置
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-02
 **/
@Data
@RefreshScope
@Configuration
@EnableAutoConfiguration(exclude = MongoAutoConfiguration.class)
@ConfigurationProperties(prefix = "reformer.gecko.flink-cdc")
@EnableConfigurationProperties(ReformerFlinkCdcProperties.class)
public class ReformerFlinkCdcProperties {

    /*** 是否启用 **/
    private Boolean enable = false;

    /*** mysql数据源配置 **/
    private List<ReformerFlinkCdcMySqlProperties> mysqlSource;
    /*** postgreSql数据源配置 **/
    private List<ReformerFlinkCdcPostgreSqlProperties> postgreSource;
    /*** mongodb数据源配置 **/
    private List<ReformerFlinkCdcMongoProperties> mongodbSource;
}
