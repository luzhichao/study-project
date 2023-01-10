package org.gecko.reformer.processor;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 降级类方法数据
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-03
 **/
@Data
class FallbackClassMethodInfo implements Serializable {
    private static final long serialVersionUID = 8761278527492189800L;

    /*** 方法返回类型 **/
    private String returnType;
    /*** 方法返回简单类型 **/
    private String returnSimpleType;
    /*** 方法名 **/
    private String name;
    /*** 方法参数 **/
    private String params;
    /*** 方法异常 **/
    private String throwList;
}
