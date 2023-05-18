package com.reformer.config;

import com.reformer.annotation.NoProguard;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * 描述:
 * 作者: LiuCheng
 * 时间: 2023/5/18 13:26
 */
@Data
@RefreshScope
@Configuration
@NoProguard
@ConfigurationProperties(prefix = "reformer.geo.config")
public class GeoConfig {

    /**
     * 描述: 缓存的KEY值
     */
    private String redisKey = "coordinate";

    /**
     * 描述: 请求高德时携带的key
     */
    private String httpKey = "6f8292bb77805311993d605e149cdb5b";

    /**
     * 描述: 请求高德时的URL
     */
    private String httpUrl = "https://restapi.amap.com/v3/geocode/regeo?";
}
