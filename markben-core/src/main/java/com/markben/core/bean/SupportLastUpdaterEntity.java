package com.markben.core.bean;

/**
 * 支持最后一次更新者实体类
 * @author 乌草坡
 * @since 0.0.1
 */
public interface SupportLastUpdaterEntity {

    /**
     * 设置最后一次更新者
     * @param lastUpdater 最后一次更新者
     */
    void setLastUpdater(String lastUpdater);

    /**
     * 最后一次更新者
     * @return 返回最后一次更新者
     */
    String getLastUpdater();

}
