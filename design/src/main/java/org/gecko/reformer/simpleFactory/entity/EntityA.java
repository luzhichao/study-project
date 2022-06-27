package org.gecko.reformer.simpleFactory.entity;

import lombok.Data;

/**
 * 子类A
 *
 * @author LZC
 * @version 1.0.0
 * @date 2022-06-25
 **/
@Data
public class EntityA extends AbsEntity {
    private static final long serialVersionUID = 1L;

    private String name;
    private Boolean agree;

}
