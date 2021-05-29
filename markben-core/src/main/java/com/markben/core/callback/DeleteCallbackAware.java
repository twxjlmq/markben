package com.markben.core.callback;

import com.markben.core.mapper.BaseEnhanceMapper;

import java.io.Serializable;

/**
 * 删除数据时的回调接口
 * @author 乌草坡
 * @since 0.0.1
 * @param <T>
 */
public interface DeleteCallbackAware<T extends BaseEnhanceMapper, V extends Serializable> extends CallbackAware<T, V> {

}
