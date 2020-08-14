package com.markben.beans.validator.hibernate;

import com.markben.beans.validator.IBeanValidator;
import com.markben.common.enable.ICheckable;
import com.markben.common.exception.NullArgumentException;
import com.markben.common.logger.ILogger;
import com.markben.common.utils.LoggerUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * hibernate验证工具类
 * @author 乌草坡
 * @since 1.0
 */
public class HibernateBeanValidator implements IBeanValidator {

    private static final ILogger logger = LoggerUtils.getLogger(HibernateBeanValidator.class);

    private static Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * 验证参数
     * @param obj
     */
    public void validate(ICheckable obj) {
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
