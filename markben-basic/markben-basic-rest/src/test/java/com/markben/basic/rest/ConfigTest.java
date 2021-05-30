package com.markben.basic.rest;

import com.markben.cache.ICacheManager;
import com.markben.cache.ehcache.EhCacheManager;
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
