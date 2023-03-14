package org.gecko.reformer.domain;

import lombok.Data;
import org.gecko.reformer.constant.OpMongoType;

/**
 * mongodb数据变更反序列化DO
 * {@link com.mongodb.client.model.changestream.ChangeStreamDocument}
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-06
 **/
@Data
public class MongoDataChangeDO extends BaseChangeDo {
    private static final long serialVersionUID = 1L;

    /*** 数据id **/
    private String id;
    /*** 是否是复制已存在数据 **/
    private Boolean copyingData;
    /*** 数据标识 **/
    private String data;
    /*** 操作类型 **/
    private OpMongoType op;
    /*** 全部文档数据 **/
    private String fullDoc;
    /*** 数据库名 **/
    private String database;
    /*** 集合名 **/
    private String collect;
    /*** 变更描述 **/
    private MongoUpdateDescDO updateDesc;
    /*** 事件变更时间戳 **/
    private Long tsMs;
}
