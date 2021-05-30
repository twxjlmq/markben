package com.markben.core.callback;

import com.markben.core.bean.EntityBean;
import com.markben.core.dao.BaseDao;

/**
 * 更新回调接口；实现类该接口的实现类；在实体类被更新时，会执行callback方法
 * @author 乌草坡
 * @since 0.0.1
 * @param <T>
 * @param <V>
 */
public interface UpdateCallbackAware<T extends BaseDao, V extends EntityBean> extends CallbackAware<T, V> {

    
}
