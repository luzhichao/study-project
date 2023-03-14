package org.gecko.reformer.domain;

import lombok.Data;
import org.gecko.reformer.constant.OpMongoType;

/**
 * mongodb数据变更元数据
 * {@link com.mongodb.client.model.changestream.ChangeStreamDocument}
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-06
 **/
@Data
public class MongoSourceDO extends BaseChangeDo {
    private static final long serialVersionUID = 1L;

    /*** 数据标识 **/
    private String data;
    /*** 操作类型 **/
    private OpMongoType op;
    /*** 数据库名 **/
    private String database;
    /*** 集合名 **/
    private String collect;
    /*** 事件变更时间戳 **/
    private Long tsMs;
}
