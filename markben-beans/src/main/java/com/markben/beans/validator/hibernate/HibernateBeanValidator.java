package com.markben.beans.validator.hibernate;

import com.markben.beans.validator.BeanValidator;
import com.markben.common.enable.Checkable;
import com.markben.common.logger.Logger;
import com.markben.common.utils.LoggerUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * hibernate验证工具类
 * @author 乌草坡
 * @since 0.0.1
 */
public class HibernateBeanValidator implements BeanValidator {

    private static final Logger logger = LoggerUtils.getLogger(HibernateBeanValidator.class);

    private static Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * 验证参数
     * @param obj
     */
    public void validate(Checkable obj) {
        if(null == obj) {
            LoggerUtils.warn(logger, "参数对象为空");
            return;
        }
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);
        if(constraintViolations.size() > 0) {
            throw new IllegalArgumentException(constraintViolations.iterator().next().getMessage());
        }
    }
}
