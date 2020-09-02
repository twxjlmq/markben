package com.markben.core.validator;

import com.markben.common.enable.ICheckable;

/**
 * Bean自己实现验证功能；
 * 需要实现该接口
 * @author 乌草坡
 * @since 1.0
 */
public interface IBeanSelfValidator extends ICheckable {

    /**
     * 验证方法
     */
    void validate();

}
