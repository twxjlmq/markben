package com.markben.core.bean;

import java.util.Date;

/**
 * 更新时间---接口；
 * 如果实体类有更新日期字段，需要系统自动生成更新时间，那么实现该接口
 * @autor 乌草坡
 * @since 1.0
 */
public interface IUpdateTime {

    /**
     * 获取更新时间
     * @return 返回更新时间
     */
    Date getUpdateTime();

    /**
     * 设置更新时间
     * @param updateTime 更新时间
     */
    void setUpdateTime(Date updateTime);

}
