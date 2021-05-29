package com.markben.beans.validator;

import com.markben.beans.validator.hibernate.HibernateBeanValidator;

/**
 * Bean验证工厂
 * @author 乌草坡
 * @since 0.0.1
 */
public class BeanValidatorFactory {

    private BeanValidator beanValidator;

    private static BeanValidatorFactory instance = new BeanValidatorFactory();

    private void setBeanValidator(BeanValidator beanValidator) {
        this.beanValidator = beanValidator;
    }

    /**
     * 设置验证器
     * @param validator 验证器实现类
     * @return 返回验证器工厂实例
     */
    public static BeanValidatorFactory setValidator(BeanValidator validator) {
        instance.setBeanValidator(validator);
        return instance;
    }

    /**
     * 使用默认验证器
     * @return 返回验证器工厂实例
     */
    public static BeanValidatorFactory defaultValidator() {
        instance.setBeanValidator(new HibernateBeanValidator());
        return instance;
    }

    /**
     * 构建验证器
     * @return 返回Bean验证器
     */
    public BeanValidator build() {
        return beanValidator;
    }


}
