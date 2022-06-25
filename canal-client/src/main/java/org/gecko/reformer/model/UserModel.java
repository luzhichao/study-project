package org.gecko.reformer.model;

import lombok.Data;
import org.gecko.reformer.annotation.CanalColumn;
import org.gecko.reformer.annotation.CanalTable;

import java.io.Serializable;
import java.util.Date;

/**
 * 测试实体类
 *
 * @author LZC
 * @version 1.0.0
 * @date 2022-04-24
 **/
@Data
@CanalTable("test_two")
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @CanalColumn("id")
    private String id;
    @CanalColumn("name")
    private String name;
}