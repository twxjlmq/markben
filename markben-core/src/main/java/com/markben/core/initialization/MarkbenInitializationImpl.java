package com.markben.core.initialization;

import com.markben.core.context.MarkbenContext;
import com.markben.core.context.MarkbenContextFactory;

import java.util.Collection;
import java.util.Observable;

/**
 * 框架初始化接口实现类；继承了Observable类
 * @autor 乌草坡
 * @since 0.0.1
 */
public class MarkbenInitializationImpl extends Observable implements MarkbenInitialization {

    @Override
    public void init() {
        MarkbenContext context = MarkbenContextFactory.getContext();
        Collection<MarkbenInitializeListener> listeners = context.findsAndOrder(MarkbenInitializeListener.class);
        MarkbenInitializeObserver observer = new MarkbenInitializeObserver(listeners);
        super.addObserver(observer);
        super.setChanged();
        super.notifyObservers();
    }
}
