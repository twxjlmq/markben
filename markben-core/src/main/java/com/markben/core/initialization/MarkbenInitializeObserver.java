package com.markben.core.initialization;

import com.markben.cache.ICacheManager;
import com.markben.cache.ICacheManagerAware;
import com.markben.common.logger.ILogger;
import com.markben.common.utils.CollectionUtils;
import com.markben.common.utils.LoggerUtils;
import com.markben.core.config.IMarkbenConfiguration;
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

    private Collection<IMarkbenInitializeListener> listeners;

    public MarkbenInitializeObserver(Collection<IMarkbenInitializeListener> listeners) {
        this.listeners = listeners;
    }

    @Override
    public void update(Observable o, Object arg) {
        handleContextAware();
        ICacheManager cacheManager = (ICacheManager) arg;
        handleCacheManagerAware(cacheManager);
        LoggerUtils.debug(logger, "正在处理初始化实例...");
        LoggerUtils.debug(logger, "实现处理初始化的有[{}]个实例.", (null == listeners ? 0 : listeners.size()));
        if(CollectionUtils.isNotEmpty(listeners)) {
            for (IMarkbenInitializeListener listener : listeners) {
                listener.initialize();
            }
        }
    }

    /**
     * 处理缓存接口实现类
     * @param cacheManager
     */
    private void handleCacheManagerAware(ICacheManager cacheManager) {
        LoggerUtils.debug(logger, "正在初始化处理缓存管理实例...");
        List<ICacheManagerAware> cacheManagerAwares = MarkbenContextFactory.finds(ICacheManagerAware.class);
        LoggerUtils.debug(logger, "实现缓存管理的[{}]个实例.", (null == cacheManagerAwares ? 0 :cacheManagerAwares.size()));
        if(CollectionUtils.isNotEmpty(cacheManagerAwares)) {
            for(ICacheManagerAware cacheManagerAware : cacheManagerAwares) {
                cacheManagerAware.setCacheManager(cacheManager);
            }
        }
    }

    /**
     * 处理上下文接口实现类
     */
    private void handleContextAware() {
        LoggerUtils.debug(logger, "正在初始化IMarkbenContextAware接口实现类...");
        List<IMarkbenContextAware> contextAwares = MarkbenContextFactory.finds(IMarkbenContextAware.class);
        LoggerUtils.debug(logger, "实现IMarkbenContextAware接口的有[{}]个实例.", (null == contextAwares ? 0 :contextAwares.size()));
        if(CollectionUtils.isNotEmpty(contextAwares)) {
            for (IMarkbenContextAware contextAware : contextAwares) {
                if(contextAware instanceof IMarkbenConfiguration) {
                    //因为配置接口类在Spring初始化完的时候就已经处理过，这里不再处理
                    continue;
                }
                contextAware.setContext(MarkbenContextFactory.getContext());
            }
        }
    }
}
