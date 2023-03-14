package org.gecko.reformer.dto;

import lombok.Data;
import org.gecko.reformer.constant.OpErrorEnum;

import java.util.Set;

/**
 * fink mongodb数据源配置DTO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-02
 **/
@Data
public class ReformerFlinkMongoDTO extends ReformerFlinkBaseDTO {

    /*** MongoDB服务器的主机名和端口对，多个逗号分隔 **/
    private String hosts;
    /*** 用户名 **/
    private String username;
    /*** 密码 **/
    private String password;
    /*** 数据库名，支持正则表达式，以监视与正则表达式匹配的多个数据库。 **/
    private Set<String> databases;
    /*** 集合名(数据库名.集合名)，支持正则表达式来监视与完全限定的集合标识符匹配的多个集合 **/
    private Set<String> collects;
    /*** 如果遇到错误，是否继续处理消息。设置为none时，连接器会报告错误，并在遇到错误时阻止对其余记录的进一步处理；设置为all时，连接器会自动忽略任何错误消息。 **/
    private OpErrorEnum errorsTolerance = OpErrorEnum.ALL;
    /*** 处理新数据时，单个批处理中要包含的更改流文档的最大数量(默认1000)。 **/
    private Integer batchSize = 1000;
    /*** 最大批处理数(默认1000) **/
    private Integer pollMaxBatchSize = 1000;
    /*** 检查变更等待的时间(毫秒，默认1500)。 **/
    private Integer awaitTime = 1500;
    /*** 检查变更偏移心跳时间(毫秒，默认30000) **/
    private Integer heartbeatInterval = 30000;
    /*** 连接选项，例如="replicaSet=test&connectTimeoutMS=300000" **/
    private String connectionOptions;
    /*** 是否复制现有数据，默认true **/
    private Boolean copyExisting = true;
}
