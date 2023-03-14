package org.gecko.reformer.dto;

import lombok.Data;
import org.gecko.reformer.constant.MysqlStartupMode;

import java.util.Set;

/**
 * fink mysql数据源配置
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-02
 **/
@Data
public class ReformerFlinkMySqlDTO extends ReformerFlinkBaseDTO {

    /*** mysql主机地址 **/
    private String hostname;
    /*** mysql端口 **/
    private int port = 3306;
    /*** 数据库名称，支持正则表达式，以监视与正则表达式匹配的多个数据库 **/
    private Set<String> databases;
    /*** 表名(数据库名.表名)，支持正则表达式，以监视与正则表达式匹配的多个表 **/
    private Set<String> tables;
    /*** 用户名 **/
    private String username;
    /*** 密码 **/
    private String password;
    /*** 连接池大小(默认10) **/
    private int connectionPoolSize = 10;
    /*** 检查binlog偏移心跳时间(秒，默认30秒) **/
    private long heartbeatInterval = 30;
    /*** 连接超时时间(秒，默认30秒) **/
    private long connectTimeout = 30;
    /*** 连接等待时间(秒，默认3秒) **/
    private int connectMaxRetries = 3;
    /*** 数据库时区：Asia/Shanghai **/
    private String serverTimeZone = "Asia/Shanghai";
    /*** 执行模式 **/
    private MysqlStartupMode optionMode;
}
