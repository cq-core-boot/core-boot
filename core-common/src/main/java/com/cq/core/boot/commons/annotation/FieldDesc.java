package com.cq.core.boot.commons.annotation;

/***
 * 字段定义
 *
 * @author cqmike
 * @since 1.0.0
 * @return
 */
public @interface FieldDesc {
    String name() default "";
}
