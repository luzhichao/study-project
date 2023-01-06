package org.gecko.reformer.annotation;

import java.lang.annotation.*;

/**
 * Feign服务自动降级注解
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-03
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface AutoFeignFallback {
}
