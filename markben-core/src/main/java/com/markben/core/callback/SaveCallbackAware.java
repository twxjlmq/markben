package com.markben.core.callback;

import com.markben.core.bean.EntityBean;
import com.markben.core.dao.BaseDao;

/**
 * 保存回调接口；实现该接口的实现类，当有实体对象被保存时，触发该接口的callback方法
 * @author 乌草坡
 * @since 0.0.1
 * @param <T>
 * @param <V>
 */
public interface SaveCallbackAware<T extends BaseDao, V extends EntityBean> extends CallbackAware<T, V> {

    
}
