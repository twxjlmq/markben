package com.markben.core.config;

import com.markben.cache.ICacheManager;
import com.markben.core.context.IMarkbenContext;
import com.markben.core.context.IMarkbenContextAware;
import com.markben.core.context.MarkbenContextFactory;
import org.springframework.stereotype.Component;

/**
 * 默认markben配置接口实现类
 * @author 乌草坡
 * @since 1.0
 */
public class DefaultMarkbenConfiguration implements IMarkbenConfiguration {

    private IMarkbenContext context;

    public DefaultMarkbenConfiguration(IMarkbenContext context) {
        this.context = context;
    }

    @Override
    public ICacheManager getCacheManager() {
        return getContext().find(ICacheManager.class);
    }

    public IMarkbenContext getContext() {
        return context;
    }
}
