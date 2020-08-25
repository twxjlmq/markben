package com.markben.core.config;

import com.markben.cache.ICacheManager;

/**
 * markben配置接口类
 * @author 乌草坡
 * @since 0.1
 */
public interface IMarkbenConfiguration {

    /**
     * 获取缓存管理实例
     * @return 返回缓存管理实例对象
     */
    ICacheManager getCacheManager();

}
