package com.markben.core.initialization;

import com.markben.core.cache.ICacheManager;
import com.markben.core.cache.ehcache.EhCacheManager;
import com.markben.core.context.IMarkbenContext;
import com.markben.core.context.MarkbenContextFactory;

import java.util.Collection;
import java.util.Observable;

/**
 * 框架初始化接口实现类；继承了Observable类
 * @autor 乌草坡
 * @since 1.0
 */
public class MarkbenInitializationImpl extends Observable implements IMarkbenInitialization {

    private ICacheManager cacheManager;

    public MarkbenInitializationImpl() {
    }

    public MarkbenInitializationImpl(ICacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void init() {
        if(null == cacheManager) {
            cacheManager = new EhCacheManager();
        }
        IMarkbenContext context = MarkbenContextFactory.getContext();
        Collection<IMarkbenInitializeListener> listeners = context.findsAndOrder(IMarkbenInitializeListener.class);
        MarkbenInitializeObserver observer = new MarkbenInitializeObserver(listeners);
        super.addObserver(observer);
        super.setChanged();
        super.notifyObservers(cacheManager);
    }

    public void setCacheManager(ICacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
}
