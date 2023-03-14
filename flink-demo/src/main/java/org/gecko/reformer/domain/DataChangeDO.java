package org.gecko.reformer.domain;

import lombok.Data;
import org.gecko.reformer.constant.OpType;

/**
 * 数据变更反序列化DO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-06
 **/
@Data
public class DataChangeDO extends BaseChangeDo {
    private static final long serialVersionUID = 1L;

    /*** 更新前数据，原始数据 **/
    private String before;
    /*** 更新后数据，新数据 **/
    private String after;
    /*** 事件变更元信息，包括：数据库名、表名、Binlog读取位置等 **/
    private SourceDO source;
    /*** 操作类型(c、u、d) **/
    private OpType op;
    /*** 事件变更时间戳 **/
    private Long tsMs;
}
