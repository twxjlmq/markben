package com.markben.core.config;

import com.markben.cache.ICacheManager;
import com.markben.common.config.AbstractConfig;
import com.markben.common.config.SystemConfig;
import com.markben.common.logger.ILogger;
import com.markben.common.utils.LoaderClassUtils;
import com.markben.common.utils.LoggerUtils;
import com.markben.core.context.IMarkbenContext;
import com.markben.core.context.IMarkbenContextAware;
import com.markben.core.context.MarkbenContextFactory;
import com.markben.core.exception.SystemInitializationException;
import com.markben.core.initialization.IMarkbenInitializeListener;
import org.springframework.stereotype.Component;

/**
 * 默认markben配置接口实现类
 * @author 乌草坡
 * @since 1.0
 */
public class DefaultMarkbenConfiguration implements IMarkbenConfiguration, IMarkbenInitializeListener {

    private static final ILogger logger = LoggerUtils.getLogger(DefaultMarkbenConfiguration.class);

    private IMarkbenContext context;

    private AbstractConfig systemConfig;

    private ICacheManager cacheManager;

    public DefaultMarkbenConfiguration(IMarkbenContext context) {
        this.context = context;
    }

    @Override
    public void initialize() {
        LoggerUtils.debug(logger, "正在初始化系统配置...");
        if(null == getContext()) {
            throw new SystemInitializationException("IMixSmartContext接口对象为null，无法初始化系统...");
        }
        initCacheManager();
        initSystemConfig();
    }

    /**
     * 初始化缓存管理
     */
    private void initCacheManager() {
        LoggerUtils.debug(logger, "正在初始化ICacheManager接口实现类...");
        ICacheManager cacheManager = getContext().find(ICacheManager.class);
        if(null == cacheManager) {
            //尝试着加载本地内存缓存策略
            String classPath = "com.markben.cache.ehcache.EhCacheManager";
            cacheManager = LoaderClassUtils.tryLoadingAndInstance(classPath, null);
            if(null == cacheManager) {
                LoggerUtils.warn(logger, "未找到缓存接口(ICacheManager)的实现类，将无法使用缓存功能");
            }
        }
        LoggerUtils.debug(logger, "ICacheManager接口实现类为:[{}].", cacheManager.toString());
        this.cacheManager = cacheManager;
    }

    /**
     * 初始化系统配置文件类
     */
    private void initSystemConfig() {
        this.systemConfig  = SystemConfig.getInstance();
    }

    @Override
    public ICacheManager getCacheManager() {
        return this.cacheManager;
    }

    public IMarkbenContext getContext() {
        return context;
    }

    @Override
    public AbstractConfig getSystemConfig() {
        return systemConfig;
    }

    public void setSystemConfig(AbstractConfig systemConfig) {
        this.systemConfig = systemConfig;
    }

    public void setCacheManager(ICacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
}
