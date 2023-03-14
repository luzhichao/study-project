package org.gecko.reformer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * mongodb数据变更描述DO
 * {@link com.mongodb.client.model.changestream.UpdateDescription}
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-06
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoUpdateDescDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /*** 更新的属性:值 **/
    private Map<String, Object> updatedFields;
    /*** 删除的属性 **/
    private List<String> removedFields;
}
