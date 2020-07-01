package com.jack.jdbc.jdbc.annonation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JDBCTable {
    String value() default "";
}
