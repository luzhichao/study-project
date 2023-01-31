package org.gecko.reformer.annotation;

import java.lang.annotation.*;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-03
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MyFeignClient {

    String value() default "";

    @Deprecated
    String serviceId() default "";

    String contextId() default "";

    String name() default "";

    @Deprecated
    String qualifier() default "";

    String[] qualifiers() default {};

    String url() default "";

    boolean decode404() default false;

    Class<?>[] configuration() default {};

    Class<?> fallback() default void.class;

    Class<?> fallbackFactory() default void.class;

    String path() default "";

    boolean primary() default true;

}

