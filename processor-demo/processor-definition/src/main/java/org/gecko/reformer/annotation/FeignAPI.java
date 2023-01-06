package org.gecko.reformer.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义Feign API注解
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-03
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface FeignAPI {
}
