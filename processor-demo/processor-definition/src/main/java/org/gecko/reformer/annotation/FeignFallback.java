package org.gecko.reformer.annotation;

import java.lang.annotation.*;

/**
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-03
 **/
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface FeignFallback {
}
