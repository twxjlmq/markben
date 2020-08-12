package com.markben.basic.common;

import com.markben.cache.ICache;
import com.markben.cache.ICacheManager;
import com.markben.cache.ehcache.EhCacheManager;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 乌草坡
 * @since 1.0
 */
@Configuration
public class ConfigTest {

    @Bean
    public ICacheManager createCacheManager() {
        return new EhCacheManager();
    }

}
