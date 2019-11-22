package com.zhuhong.inspection.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author 叶剑
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface SystemLog {

    String description() default "";

    String type() default "";

}
