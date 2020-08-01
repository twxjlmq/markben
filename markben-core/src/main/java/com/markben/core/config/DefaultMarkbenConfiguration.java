package com.markben.core.config;

import com.markben.cache.ICacheManager;
import com.markben.core.context.IMarkbenContext;
import com.markben.core.context.MarkbenContextFactory;

/**
 * 默认markben配置接口实现类
 * @author 乌草坡
 * @since 1.0
 */
public class DefaultMarkbenConfiguration implements IMarkbenConfiguration {

    private IMarkbenContext markbenContext;

    public DefaultMarkbenConfiguration(IMarkbenContext markbenContext) {
        this.markbenContext = markbenContext;
    }

    @Override
    public ICacheManager getCacheManager() {
        return markbenContext.find(ICacheManager.class);
    }
}
