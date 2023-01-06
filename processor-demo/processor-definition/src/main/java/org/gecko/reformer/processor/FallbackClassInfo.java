package org.gecko.reformer.processor;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 降级类数据
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-03
 **/
@Data
class FallbackClassInfo implements Serializable {
    private static final long serialVersionUID = 8761278527492189800L;

    /*** 所在包名 **/
    private String packageName;
    /*** 需要引入的包 **/
    private List<String> imports;
    /*** 类注解 **/
    private List<String> annotations;
    /*** 创建的类名称 **/
    private String className;
    /*** 实现接口名称集合 **/
    private List<String> implementsClassName;
    /*** 泛型的名称 **/
    private String genericsName;
    /*** 方法列表 **/
    private List<String> methods;
}
