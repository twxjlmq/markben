package com.markben.beans.validator.hibernate;

import com.markben.common.utils.ArrayUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 整型数据类型范围验证器
 * @author 乌草坡
 * @since 0.0.1
 */
public class IntValueContainValidator implements ConstraintValidator<IntValueContain, Integer> {

    private Integer[] scopeValues;

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return ArrayUtils.isArrayContains(scopeValues, value);
    }

    @Override
    public void initialize(IntValueContain constraintAnnotation) {
        int[] values = constraintAnnotation.value();
        this.scopeValues = IntStream.of(values).boxed().collect(Collectors.toList()).toArray(new Integer[0]);
    }
}
