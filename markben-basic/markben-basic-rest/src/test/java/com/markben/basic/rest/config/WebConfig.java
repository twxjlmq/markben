package com.markben.basic.rest.config;

import com.markben.rest.common.SnakeToCamelArgumentResolver;
import com.markben.rest.common.interceptor.DefaultRestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * SpringMVC配置类
 * @author 乌草坡
 * @since 1.0
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        DefaultRestInterceptor restInterceptor = new DefaultRestInterceptor();
        registry.addInterceptor(restInterceptor)
                .excludePathPatterns("/rest/user/login", "/rest/user/confirm/login")
                .addPathPatterns("/rest/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger/**").addResourceLocations("/swagger/");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new SnakeToCamelArgumentResolver());
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(new MappingJackson2HttpMessageConverter());
        converters.add(new StringHttpMessageConverter());
    }
}
