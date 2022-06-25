package org.gecko.reform.demo.config;

import lombok.Data;
import org.gecko.reform.demo.constants.Database;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 自动建表配置
 *
 * @author LZC
 * @version 1.0.0
 * @date 2021/06/30
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "reformer.gecko.data-base")
@EnableConfigurationProperties(DataBaseProperties.class)
public class DataBaseProperties {

    /**
     * 数据库类型，默认MySQL
     */
    private Database databaseType = Database.MYSQL;
    /**
     * 数据库IP地址
     **/
    private String host;
    /**
     * 数据库端口
     **/
    private String port = "3306";
    /**
     * 数据库空间
     */
    private String dataBase;
    /**
     * 用户名
     **/
    private String username;
    /**
     * 密码
     **/
    private String password;

}
