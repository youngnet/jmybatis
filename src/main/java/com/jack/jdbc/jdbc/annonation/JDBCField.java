package com.jack.jdbc.jdbc.annonation;

import java.lang.annotation.*;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JDBCField {
    String value() default "";
}
