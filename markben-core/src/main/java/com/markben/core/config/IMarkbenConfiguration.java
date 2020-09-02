package com.markben.core.config;

import com.markben.cache.ICacheManager;
import com.markben.common.config.AbstractConfig;
import com.markben.core.multipart.IMultipartManager;
import com.markben.core.validator.IBeanValidator;

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

    /**
     * 获取系统配置文件信息实现类
     * @return 返回实现对象
     */
    AbstractConfig getSystemConfig();

    /**
     * 获取bean验证器
     * @return 返回验证实现类；如果没有获取到返回null
     */
    IBeanValidator getBeanValidator();

    /**
     * 获取Multipart管理接口实现类
     * @return 返回实现对象
     */
    IMultipartManager getMultipartManager();

}
