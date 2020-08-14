package com.markben.beans.validator.hibernate;

import com.markben.common.utils.ArrayUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author 乌草坡
 * @since 1.0
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
