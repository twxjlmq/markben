package com.markben.core.bean;

/**
 * 有状态的实体接口类
 * @author 乌草坡
 * @since 0.0.1
 */
public interface SupportStateEntity {

    /**
     * 获取状态
     * @return 返回状态
     */
    Integer getState();

    /**
     * 设置状态
     * @param state 状态值
     */
    void setState(Integer state);

}
