package com.markben.core.initialization;

import com.markben.cache.ICacheManager;
import com.markben.cache.ICacheManagerAware;
import com.markben.common.logger.ILogger;
import com.markben.common.utils.CollectionUtils;
import com.markben.common.utils.LoggerUtils;
import com.markben.core.config.IMarkbenConfiguration;
import com.markben.core.context.IMarkbenContext;
import com.markben.core.context.IMarkbenContextAware;
import com.markben.core.context.MarkbenContextFactory;

import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * 框架初始化观察者
 * @autor 乌草坡
 * @since 1.0
 */
public class MarkbenInitializeObserver implements Observer {

    private static final ILogger logger = LoggerUtils.getLogger(MarkbenInitializeObserver.class);

    private Collection<IMarkbenInitializeAware> listeners;

    public MarkbenInitializeObserver(Collection<IMarkbenInitializeAware> listeners) {
        this.listeners = listeners;
    }

    @Override
    public void update(Observable o, Object arg) {
        LoggerUtils.debug(logger, "正在处理初始化实例...");
        initContextAware();
        initCacheManagerAware();
        LoggerUtils.debug(logger, "实现处理初始化的有[{}]个实例.", (null == listeners ? 0 : listeners.size()));
        if(CollectionUtils.isNotEmpty(listeners)) {
            for (IMarkbenInitializeAware listener : listeners) {
                listener.initialize();
            }
        }
    }

    /**
     * 初始化上下文接口实现类
     */
    private void initContextAware() {
        LoggerUtils.debug(logger, "正在初始化IMarkbenContextAware接口实现类...");
        List<IMarkbenContextAware> contextAwareList = MarkbenContextFactory.finds(IMarkbenContextAware.class);
        LoggerUtils.debug(logger, "实现IMarkbenContextAware接口的有[{}]个实例.", (null == contextAwareList ? 0 :contextAwareList.size()));
        if(CollectionUtils.isNotEmpty(contextAwareList)) {
            IMarkbenContext context = MarkbenContextFactory.getContext();
            for (IMarkbenContextAware contextAware : contextAwareList) {
                if(contextAware instanceof IMarkbenConfiguration) {
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
                LoggerUtils.warn(logger, "没有找到缓存接口实现类，无法使用缓存...");
                return;
            }
            for(ICacheManagerAware cacheManagerAware : cacheManagerAwareList) {
                cacheManagerAware.setCacheManager(cacheManager);
            }
        }
    }


}
