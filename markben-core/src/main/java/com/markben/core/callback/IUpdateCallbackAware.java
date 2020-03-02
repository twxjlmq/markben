package com.markben.core.callback;

import com.markben.core.bean.IEntityBean;
import com.markben.core.mapper.BaseEnhanceMapper;

/**
 * 更新回调接口；实现类该接口的实现类；在实体类被更新时，会执行callback方法
 * @author 乌草坡
 * @since 1.0
 * @param <T>
 * @param <V>
 */
public interface IUpdateCallbackAware<T extends BaseEnhanceMapper, V extends IEntityBean> extends ICallbackAware<T, V> {

    
}
