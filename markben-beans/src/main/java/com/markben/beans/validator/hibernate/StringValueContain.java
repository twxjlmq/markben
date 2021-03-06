package com.markben.beans.validator.hibernate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 值包含验证（字符串）
 * @author 乌草坡
 * @since 0.0.1
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StringValueContainValidator.class)
@Documented
public @interface StringValueContain {

    String message() default "{markben.validator.value.contain.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] value();

}
