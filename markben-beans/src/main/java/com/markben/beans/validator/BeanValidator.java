package com.markben.beans.validator;

import com.markben.common.enable.Checkable;

/**
 * 验证对象接口
 * @author 乌草坡
 * @since 0.0.1
 */
public interface BeanValidator {

    /**
     * 验证对象
     * @param object 验证的对象
     */
    void validate(Checkable object);

}
