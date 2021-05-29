package com.markben.core.validator;

import com.markben.common.enable.Checkable;

/**
 * Bean自己实现验证功能；
 * 需要实现该接口
 * @author 乌草坡
 * @since 0.0.1
 */
public interface BeanSelfValidator extends Checkable {

    /**
     * 验证方法
     */
    void validate();

}
