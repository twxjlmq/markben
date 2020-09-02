package com.markben.core.spring;

import com.markben.common.logger.ILogger;
import com.markben.common.utils.CollectionUtils;
import com.markben.common.utils.LoggerUtils;
import com.markben.common.utils.StringUtils;
import com.markben.core.context.IMarkbenContext;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Markben框架上下文Spring实现类
 * @autor 乌草坡 2020-03-02
 * @since 1.0
 */
public class SpringMarkbenContext implements IMarkbenContext {

    private static final ILogger logger = LoggerUtils.getLogger(SpringMarkbenContext.class);

    //用于实现自定义注册类
    Map<String, Object> registerMap = Collections.synchronizedMap(new HashMap<>());

    private ApplicationContext applicationContext;
    private DefaultListableBeanFactory beanFactory;

    public SpringMarkbenContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
    }

    @Override
    public void put(String name, Object obj) {
        //StringUtils.isAssert(obj, "obj参数不能为空");
        if(StringUtils.isEmpty(name) || null == obj) {
            LoggerUtils.warn(logger, "name或obj参数为空(null)，无法注册实例...");
            return;
        }
        if(applicationContext.containsBean(name)) {
            LoggerUtils.warn(logger, "[{}]该名称已存在，不需要重复注册...");
            return;
        }
        registerMap.putIfAbsent(name, obj);
    }

    @Override
    public void put(Object obj) {
        if(null == obj) {
            LoggerUtils.warn(logger, "实例为null，无法注册...");
            return;
        }
        String name = obj.getClass().getSimpleName();
        put(name, obj);
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
        boolean is = applicationContext.containsBean(name);
        if(!is) {
            is = registerMap.containsKey(name);
        }
        return is;
    }

    @Override
    public <T> T find(Class<T> clazz) {
        T t = null;
        try {
            t = applicationContext.getBean(clazz);
        } catch (Exception ex) {}
        if(null == t) {
            Object obj = getObjectByClass(clazz);
            if(null != obj) {
                t = (T)obj;
            }
        }
        return t;
    }

    @Override
    public <T> List<T> finds(Class<T> clazz) {
        StringUtils.isAssert(clazz, "clazz参数不能为空");
        String[] beanNames = applicationContext.getBeanNamesForType(clazz);
        List<T> beans = new ArrayList<T>();
        if(null != beanNames && beanNames.length>0) {
            for (String beanName : beanNames) {
                beans.add(applicationContext.getBean(beanName, clazz));
            }
        }
        List<Object> list = getObjectListByClass(clazz);
        if(CollectionUtils.isNotEmpty(list)) {
            list.stream().forEach(o -> {
                beans.add((T)o);
            });
        }
        return CollectionUtils.isEmpty(beans) ? Collections.EMPTY_LIST : beans;
    }

    @Override
    public <T> List<T> findsAndOrder(Class<T> clazz) {
        StringUtils.isAssert(clazz, "clazz参数不能为空");
        List<T> list = finds(clazz);
        if(CollectionUtils.isEmpty(list)) {
            return Collections.EMPTY_LIST;
        }
        List<T> orderList = SpringHelper.objectOrdered(list);
        return orderList;
    }

    @Override
    public <T> T findByName(String name, Class<T> clazz) {
        StringUtils.isAssert(name, "name参数不能为空");
        StringUtils.isAssert(clazz, "clazz参数不能为空");
        T t = applicationContext.getBean(name, clazz);
        if(null == t) {
            Object obj = getObject(name, clazz);
            if(null != obj) {
                t = (T)obj;
            }
        }
        return t;
    }

    @Override
    public Object findByName(String name) {
        StringUtils.isAssert(name, "name参数不能为空");
        Object object = applicationContext.getBean(name);
        if(null == object) {
            object = getObjectByName(name);
        }
        return object;
    }

    public DefaultListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    /**
     * 获取对象根据名称及对象类型
     * @param name 名称
     * @param clazz 对象类型
     * @return 返回对象实例
     */
    private Object getObject(String name, Class clazz) {
        if(registerMap.isEmpty() || StringUtils.isEmpty(name) || null == clazz) {
            return null;
        }
        Set<Map.Entry<String, Object>> entries = registerMap.entrySet();
        for(Map.Entry<String, Object> entry : entries) {
            if(entry.getValue().equals(name) && clazz.isAssignableFrom(entry.getClass())) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * 获取对象实例根据名称
     * @param name 名称
     * @return 返回对象实例
     */
    private Object getObjectByName(String name) {
        if(registerMap.isEmpty() || StringUtils.isEmpty(name)) {
            return null;
        }
        Set<String> nameList = registerMap.keySet();
        for(String beanName : nameList) {
            if(beanName.equals(name)) {
                return registerMap.get(beanName);
            }
        }
        return null;
    }

    /**
     * 获取对象的实例根据对象类型；
     * 从自定义的注册MAP中获取
     * @param clazz
     * @return
     */
    private Object getObjectByClass(Class clazz) {
        List<Object> list = getObjectListByClass(clazz);
        if(CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 获取注册的实例列表根据对象类型；
     * 从自定义的注册MAP中获取
     * @param clazz
     * @return
     */
    private List<Object> getObjectListByClass(Class clazz) {
        if(registerMap.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        List<Object> list = registerMap.values().stream()
                .filter(a -> clazz.isAssignableFrom(a.getClass()))
                .collect(Collectors.toList());
        return list;
    }
}
