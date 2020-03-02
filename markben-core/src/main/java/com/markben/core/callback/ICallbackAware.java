package com.markben.core.callback;

/**
 * 回调接口
 * @author 乌草坡
 * @since 1.0
 * @param <T> 实体类或实体类对应主键的数据类型
 */
public interface ICallbackAware<T, V> {

    /**
     * 回调方法
     * @param target 回调调用者
     * @param value 参数值
     */
    void callback(T target, V value);
    
}
