package org.gecko.reformer.simpleFactory.entity;

import lombok.Data;

/**
 * 子类C
 *
 * @author LZC
 * @version 1.0.0
 * @date 2022-06-25
 **/
@Data
public class EntityC extends AbsEntity {
    private static final long serialVersionUID = 1L;

    private String remark;

    public EntityC(String text) {
        this.remark = text;
    }
}
