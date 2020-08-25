package com.markben.core.initialization;

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

    @Override
    public void init() {
        IMarkbenContext context = MarkbenContextFactory.getContext();
        Collection<IMarkbenInitializeAware> listeners = context.findsAndOrder(IMarkbenInitializeAware.class);
        MarkbenInitializeObserver observer = new MarkbenInitializeObserver(listeners);
        super.addObserver(observer);
        super.setChanged();
        super.notifyObservers();
    }
}
