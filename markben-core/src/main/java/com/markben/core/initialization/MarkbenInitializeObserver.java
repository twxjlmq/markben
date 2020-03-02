package com.markben.core.initialization;

import com.markben.common.utils.CollectionUtils;
import com.markben.core.cache.ICacheManager;
import com.markben.core.cache.ICacheManagerAware;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

/**
 * 框架初始化观察者
 * @autor 乌草坡 2020-03-02
 * @since 1.0
 */
public class MarkbenInitializeObserver implements Observer {

    private Collection<IMarkbenInitializeListener> listeners;

    public MarkbenInitializeObserver(Collection<IMarkbenInitializeListener> listeners) {
        this.listeners = listeners;
    }

    @Override
    public void update(Observable o, Object arg) {
        ICacheManager cacheManager = (ICacheManager) arg;
        if(CollectionUtils.isNotEmpty(listeners)) {
            for (IMarkbenInitializeListener listener : listeners) {
                if(listener instanceof ICacheManagerAware) {
                    ((ICacheManagerAware)listener).setCacheManager(cacheManager);
                }
                listener.initialize();
            }
        }
    }
}
