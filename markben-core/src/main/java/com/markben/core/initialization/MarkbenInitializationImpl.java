package com.markben.core.initialization;

import com.markben.cache.ICacheManager;
import com.markben.core.config.IMarkbenConfiguration;
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

    private IMarkbenConfiguration configuration;

    public MarkbenInitializationImpl(IMarkbenConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void init() {
        ICacheManager cacheManager = getConfiguration().getCacheManager();
        IMarkbenContext context = MarkbenContextFactory.getContext();
        Collection<IMarkbenInitializeListener> listeners = context.findsAndOrder(IMarkbenInitializeListener.class);
        MarkbenInitializeObserver observer = new MarkbenInitializeObserver(listeners);
        super.addObserver(observer);
        super.setChanged();
        super.notifyObservers(cacheManager);
    }

    public IMarkbenConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(IMarkbenConfiguration configuration) {
        this.configuration = configuration;
    }
}
