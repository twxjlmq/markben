package com.markben.core.callback;

import com.markben.core.bean.IEntityBean;
import com.markben.core.mapper.BaseEnhanceMapper;

import java.io.Serializable;

/**
 * 删除数据时的回调接口
 * @author 乌草坡
 * @since 1.0
 * @param <T>
 */
public interface IDeleteCallbackAware <T extends BaseEnhanceMapper, V extends Serializable> extends ICallbackAware<T, V>  {

}
