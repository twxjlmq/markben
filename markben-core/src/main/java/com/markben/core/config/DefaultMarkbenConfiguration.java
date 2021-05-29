package com.markben.core.config;

import com.markben.cache.ICacheManager;
import com.markben.common.config.AbstractConfig;
import com.markben.common.config.SystemConfig;
import com.markben.common.logger.Logger;
import com.markben.common.utils.LoaderClassUtils;
import com.markben.common.utils.LoggerUtils;
import com.markben.core.context.MarkbenContext;
import com.markben.core.exception.SystemInitializationException;
import com.markben.core.initialization.MarkbenInitializeListener;
import com.markben.core.multipart.MultipartManager;
import com.markben.core.validator.BeanValidator;

/**
 * 默认markben配置接口实现类
 * @author 乌草坡
 * @since 0.0.1
 */
public class DefaultMarkbenConfiguration implements MarkbenConfiguration, MarkbenInitializeListener {

    private static final Logger logger = LoggerUtils.getLogger(DefaultMarkbenConfiguration.class);

    private MarkbenContext context;

    private AbstractConfig systemConfig;

    private ICacheManager cacheManager;

    private MultipartManager multipartManager;

    private BeanValidator beanValidator;

    public DefaultMarkbenConfiguration(MarkbenContext context) {
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

    public MarkbenContext getContext() {
        return context;
    }

    @Override
    public AbstractConfig getSystemConfig() {
        return systemConfig;
    }

    @Override
    public BeanValidator getBeanValidator() {
        if(null == beanValidator) {
            beanValidator = getContext().find(BeanValidator.class);
        }
        return beanValidator;
    }

    @Override
    public MultipartManager getMultipartManager() {
        if(null == multipartManager) {
            multipartManager = getContext().find(MultipartManager.class);
        }
        return null;
    }

    public void setMultipartManager(MultipartManager multipartManager) {
        this.multipartManager = multipartManager;
    }

    public void setBeanValidator(BeanValidator beanValidator) {
        this.beanValidator = beanValidator;
    }

    public void setSystemConfig(AbstractConfig systemConfig) {
        this.systemConfig = systemConfig;
    }

    public void setCacheManager(ICacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
}
