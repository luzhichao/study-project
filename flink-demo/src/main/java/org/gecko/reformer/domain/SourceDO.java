package org.gecko.reformer.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 事件变更元信息DO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-06
 **/
@Data
public class SourceDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /*** Debezium版本号 **/
    private String version;
    /*** 连接类型 **/
    private String connector;
    /*** 名称 **/
    private String name;
    /*** 事件变更时间 **/
    private Long tsMs;
    /*** 是否是快照 **/
    private Boolean snapshot;
    /*** 数据库 **/
    private String db;
    /*** schema **/
    private String schema;
    /*** 序列 **/
    private String sequence;
    /*** 表 **/
    private String table;
    /*** 服务id **/
    private Integer serverId;
    /*** Binlog读取位置 **/
    private String file;
    /*** 偏移量 **/
    private Long pos;


    //private String gtid;
    //private Integer row;
    //private Long thread;
    //private String query;
}
