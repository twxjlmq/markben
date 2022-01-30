package com.markben.core.initialization;

import com.markben.cache.ICacheManager;
import com.markben.cache.ICacheManagerAware;
import com.markben.cache.utils.CacheManagerUtils;
import com.markben.common.logger.Logger;
import com.markben.common.utils.CollectionUtils;
import com.markben.common.utils.LoaderClassUtils;
import com.markben.common.utils.LoggerUtils;
import com.markben.core.config.MarkbenConfiguration;
import com.markben.core.context.MarkbenContext;
import com.markben.core.context.MarkbenContextAware;
import com.markben.core.context.MarkbenContextFactory;

import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * 框架初始化观察者
 * @author 乌草坡
 * @since 0.0.1
 */
public class MarkbenInitializeObserver implements Observer {

    private static final Logger logger = LoggerUtils.getLogger(MarkbenInitializeObserver.class);

    private Collection<MarkbenInitializeListener> listeners;

    public MarkbenInitializeObserver(Collection<MarkbenInitializeListener> listeners) {
        this.listeners = listeners;
    }

    @Override
    public void update(Observable o, Object arg) {
        LoggerUtils.debug(logger, "正在处理初始化实例...");
        initContextAware();
        initCacheManagerAware();
        LoggerUtils.debug(logger, "实现处理初始化的有[{}]个实例.", (null == listeners ? 0 : listeners.size()));
        if(CollectionUtils.isNotEmpty(listeners)) {
            for (MarkbenInitializeListener listener : listeners) {
                listener.initialize();
            }
        }
    }

    /**
     * 初始化上下文接口实现类
     */
    private void initContextAware() {
        LoggerUtils.debug(logger, "正在初始化MarkbenContextAware接口实现类...");
        List<MarkbenContextAware> contextAwareList = MarkbenContextFactory.finds(MarkbenContextAware.class);
        LoggerUtils.debug(logger, "实现MarkbenContextAware接口的有[{}]个实例.", (null == contextAwareList ? 0 :contextAwareList.size()));
        if(CollectionUtils.isNotEmpty(contextAwareList)) {
            MarkbenContext context = MarkbenContextFactory.getContext();
            for (MarkbenContextAware contextAware : contextAwareList) {
                if(contextAware instanceof MarkbenConfiguration) {
                    //因为配置接口类在Spring初始化完的时候就已经处理过，这里不再处理
                    //在SpringInitializationCompleteListener类中已经处理过
                    continue;
                }
                contextAware.setContext(context);
            }
        }
    }

    /**
     * 初始化缓存接口实现类
     */
    private void initCacheManagerAware() {
        LoggerUtils.debug(logger, "正在初始化处理缓存管理实例...");
        List<ICacheManagerAware> cacheManagerAwareList = MarkbenContextFactory.finds(ICacheManagerAware.class);
        LoggerUtils.debug(logger, "实现缓存管理的[{}]个实例.", (null == cacheManagerAwareList ? 0 : cacheManagerAwareList.size()));
        if(CollectionUtils.isNotEmpty(cacheManagerAwareList)) {
            ICacheManager cacheManager = MarkbenContextFactory.getConfiguration().getCacheManager();
            if(null == cacheManager) {
                LoggerUtils.debug(logger, "未配置缓存实现类，尝试使用本地ehcache缓存");
                //尝试着加载本地内存缓存策略
                String classPath = "com.markben.cache.ehcache.EhCacheManager";
                cacheManager = LoaderClassUtils.tryLoadingAndInstance(classPath, null);
                if(null == cacheManager) {
                    LoggerUtils.warn(logger, "未引入ehcache缓存实现模块，无法使用ehcache缓存");
                    LoggerUtils.warn(logger, "未找到缓存接口(ICacheManager)的实现类，将无法使用缓存功能");
                }
                LoggerUtils.warn(logger, "没有找到缓存接口实现类，无法使用缓存...");
                return;
            }
            CacheManagerUtils.setCacheManager(cacheManager);
            for(ICacheManagerAware cacheManagerAware : cacheManagerAwareList) {
                cacheManagerAware.setCacheManager(cacheManager);
            }
        }
    }


}
