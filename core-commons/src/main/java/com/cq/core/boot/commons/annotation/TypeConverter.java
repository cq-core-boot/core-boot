package com.cq.core.boot.commons.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({ElementType.FIELD})
public @interface TypeConverter {
  String toTypeFullName() default "java.lang.String";

}
