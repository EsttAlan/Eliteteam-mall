package com.cy.mall.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//这个类用来定义标记要缓存的方法
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD}) 
public @interface RequiredCache {
	String key() default "";
}
