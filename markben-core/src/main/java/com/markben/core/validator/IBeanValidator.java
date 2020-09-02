package com.markben.core.validator;

import com.markben.common.enable.ICheckable;

/**
 * 验证对象接口
 * @author 乌草坡
 * @since 1.0
 */
public interface IBeanValidator {

    /**
     * 验证对象
     * @param object 需要验证的对象
     */
    void validate(ICheckable object);

}
