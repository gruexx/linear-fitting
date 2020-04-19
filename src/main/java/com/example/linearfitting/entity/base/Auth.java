package com.example.linearfitting.entity.base;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {
    String code() default "";

    String desc() default "";
}