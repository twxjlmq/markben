package com.markben.core.spring;

import com.markben.common.logger.ILogger;
import com.markben.common.utils.LoggerUtils;
import com.markben.common.utils.StringUtils;
import com.markben.core.context.IMarkbenContext;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Markben框架上下文Spring实现类
 * @autor 乌草坡 2020-03-02
 * @since 1.0
 */
public class SpringMarkbenContext implements IMarkbenContext {

    private static final ILogger logger = LoggerUtils.getLogger(SpringMarkbenContext.class);

    private ApplicationContext applicationContext;
    private DefaultListableBeanFactory beanFactory;

    public SpringMarkbenContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
    }

    @Override
    public void put(String name, Object obj) {
        StringUtils.isAssert(obj, "obj参数不能为空");
        put(name, obj.getClass());
    }

    @Override
    public void put(String name, Class<?> clazz) {
        StringUtils.isAssert(name, "name参数不能为空");
        StringUtils.isAssert(clazz, "clazz参数不能为空");
        if(StringUtils.isNotEmpty(name) && null != clazz) {
            BeanDefinition definition = new RootBeanDefinition(clazz);
            beanFactory.registerBeanDefinition(name, definition);
        }
    }

    @Override
    public boolean isExist(String name) {
        return applicationContext.containsBean(name);
    }

    @Override
    public <T> T find(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    @Override
    public <T> List<T> finds(Class<T> clazz) {
        StringUtils.isAssert(clazz, "clazz参数不能为空");
        String[] beanNames = applicationContext.getBeanNamesForType(clazz);
        List<T> beans = null;
        if(null != beanNames && beanNames.length>0) {
            beans = new ArrayList<T>();
            for (String beanName : beanNames) {
                beans.add(applicationContext.getBean(beanName, clazz));
            }
        }
        return beans;
    }

    @Override
    public <T> List<T> findsAndOrder(Class<T> clazz) {
        StringUtils.isAssert(clazz, "clazz参数不能为空");
        List<T> list = finds(clazz);
        list = SpringHelper.objectOrdered(list);
        return list;
    }

    @Override
    public <T> T findByName(String name, Class<T> clazz) {
        StringUtils.isAssert(name, "name参数不能为空");
        StringUtils.isAssert(clazz, "clazz参数不能为空");
        return applicationContext.getBean(name, clazz);
    }

    @Override
    public Object findByName(String name) {
        StringUtils.isAssert(name, "name参数不能为空");
        return applicationContext.getBean(name);
    }

    private void errorPrompt() {
        LoggerUtils.error(logger,  "不支持添加实体类，请通过spring的注解方式添加");
    }

    public DefaultListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
