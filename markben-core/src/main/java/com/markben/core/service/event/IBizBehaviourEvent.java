package com.markben.core.service.event;

public interface IBizBehaviourEvent {

    /**
     * 事件
     * @param target 
     * @param behaviourType 行为类型
     * @param value 值
     */
    void event(final Object target, final BizBehaviourType behaviourType, Object value);
    
}
