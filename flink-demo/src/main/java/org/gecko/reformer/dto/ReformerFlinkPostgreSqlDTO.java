package org.gecko.reformer.dto;

import lombok.Data;
import org.gecko.reformer.constant.PgPluginEnum;

import java.util.Set;

/**
 * fink postgreSql数据源配置DTO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-02
 **/
@Data
public class ReformerFlinkPostgreSqlDTO extends ReformerFlinkBaseDTO {

    /*** mysql主机地址 **/
    private String hostname;
    /*** mysql端口 **/
    private int port = 5432;
    /*** 数据库名称 **/
    private String database;
    /*** schema名，支持正则表达式，以监视与正则表达式匹配的多个schema **/
    private Set<String> schemas;
    /*** 表名(schema名.表名)，支持正则表达式，以监视与正则表达式匹配的多个表 **/
    private Set<String> tables;
    /*** 用户名 **/
    private String username;
    /*** 密码 **/
    private String password;
    /*** 服务器上安装的Postgres逻辑解码插件的名称 **/
    private PgPluginEnum pluginName = PgPluginEnum.WAL_2_JSON;
    /*** PostgreSQL逻辑解码槽的名称，该槽是为特定数据库/模式的特定插件的流更改而创建的。服务器使用此插槽将事件流式传输到您正在配置的连接器。
     插槽名称必须符合PostgreSQL复制插槽命名规则，该规则规定：“每个复制插槽都有一个名称，可以包含小写字母、数字和下划线字符。” **/
    private String slotName = "flink";

}
