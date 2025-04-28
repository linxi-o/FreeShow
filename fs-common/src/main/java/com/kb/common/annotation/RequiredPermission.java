package com.kb.common.annotation;

import java.lang.annotation.*;

/**
 * @author syg
 * @version 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiredPermission {
    // 权限码
    String code() default "";
}