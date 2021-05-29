package com.markben.beans.validator.hibernate;

import com.markben.common.utils.ArrayUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 字符串值范围验证器
 * @author 乌草坡
 * @since 0.0.1
 */
public class StringValueContainValidator implements ConstraintValidator<StringValueContain, String> {

    private String[] scopeValues;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return ArrayUtils.isArrayContains(scopeValues, value);
    }

    @Override
    public void initialize(StringValueContain constraintAnnotation) {
        this.scopeValues = constraintAnnotation.value();
    }
}
