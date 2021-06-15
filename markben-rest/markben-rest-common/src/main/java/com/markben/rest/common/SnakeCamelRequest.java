package com.markben.rest.common;

import java.lang.annotation.*;

/**
 * 驼峰命名请求注解类
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface SnakeCamelRequest {

    Class<?> value() default Void.class;
    
}
