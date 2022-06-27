package org.gecko.reformer.simpleFactory.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 抽象父类
 *
 * @author LZC
 * @version 1.0.0
 * @date 2022-06-25
 **/
@Data
public class AbsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String text;
}
