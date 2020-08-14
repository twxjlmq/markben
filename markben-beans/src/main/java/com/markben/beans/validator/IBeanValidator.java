package com.markben.beans.validator;

import com.markben.common.enable.ICheckable;

/**
 * 验证对象接口
 * @author 乌草坡
 * @since 1.0
 */
public interface IBeanValidator {

    /**
     * 验证对象
     * @param object
     */
    void validate(ICheckable object);

}
