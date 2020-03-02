package com.markben.core.callback;

import com.markben.core.bean.IEntityBean;
import com.markben.core.mapper.BaseEnhanceMapper;

/**
 * 保存回调接口；实现该接口的实现类，当有实体对象被保存时，触发该接口的callback方法
 * @author 乌草坡
 * @since 1.0
 * @param <T>
 * @param <V>
 */
public interface ISaveCallbackAware<T extends BaseEnhanceMapper, V extends IEntityBean> extends ICallbackAware<T, V> {

    
}
