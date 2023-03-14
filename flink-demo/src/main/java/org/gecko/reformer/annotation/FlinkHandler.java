package org.gecko.reformer.annotation;

import java.lang.annotation.*;

/**
 * flink cdc 处理器注解
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-09
 **/
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FlinkHandler {

    /*** 任务名称 **/
    String jobName();

    /*** 数据类型class **/
    Class<?> modelClass();
}
