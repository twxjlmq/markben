package com.markben.core.context;

import com.markben.core.config.MarkbenConfiguration;

import java.util.List;

/**
 * 框架上下文工厂类
 * @autor 乌草坡
 * @since 0.0.1
 */
public class MarkbenContextFactory {

    private static MarkbenContext context;

    private static MarkbenConfiguration configuration;

    /**
     * 获取上下文
     * @return 返回上下文
     */
    public static MarkbenContext getContext() {
        return context;
    }

    /**
     * 设置上下文
     * @param context
     */
    public static void setContext(MarkbenContext context) {
        MarkbenContextFactory.context = context;
    }

    /**
     * 获取配置实现对象
     * @return 返回配置实现对象
     */
    public static MarkbenConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * 设置配置对象
     * @param configuration 配置对象
     */
    public static void setConfiguration(MarkbenConfiguration configuration) {
        MarkbenContextFactory.configuration = configuration;
    }



    /**
     * 根据服务名称，添加一个服务实例
     * @param name
     * @param obj
     */
    public static void put(String name, Object obj) {
        context.put(name, obj);
    }

    /**
     * 根据服务名称，添加一个服务类(类型)
     * @param name
     * @param clazz
     */
    public static void put(String name, Class<?> clazz) {
        context.put(name, clazz);
    }

    /**
     * 根据给定名称判断是否存在服务
     * @param name 名称
     * @return 存在返回true;否则返回false
     */
    public static boolean isExist(String name) {
        return context.isExist(name);
    }

    /**
     * 根据给定的类型查找服务实例
     * @param clazz
     * @return T
     */
    public static <T> T find(Class<T> clazz) {
        T t = null;
        try {
            t = context.find(clazz);
        } catch (Exception ex) {

        }
        return t;
    }

    /**
     * 根据给定的类型查找所有此类型的服务实例
     * @param clazz 指定返回类对象
     * @return Collection<T> 实例列表
     */
    public static <T> List<T> finds(Class<T> clazz) {
        return context.finds(clazz);
    }

    /**
     * 根据给定的类型查找所有此类型的服务实例列表并支持排序
     * @param clazz 指定返回类对象
     * @return Collection<T> 实例列表
     */
    public static <T> List<T> findsAndOrder(Class<T> clazz) {
        return context.findsAndOrder(clazz);
    }

    /**
     * 根据给定的服务名称、类型查找服务实例
     * @param name 名称
     * @param clazz 指定类对象
     * @return T 返回实例
     */
    public static <T> T findByName(String name, Class<T> clazz) {
        return context.findByName(name, clazz);
    }

    /**
     * 根据名称获取服务实例
     * @param name 名称
     * @return 返回实例
     */
    public static Object findByName(String name) {
        return context.findByName(name);
    }

    /**
     * 移除一个bean的定义
     * @param name bean名称
     */
    public static void removeBean(String name) {
        context.removeBean(name);
    }

    /**
     * 销毁一个Bean实例
     * @param bean 实例
     */
    public static void destroyBean(Object bean) {
        context.destroyBean(bean);
    }

    /**
     * 获取注册的数量
     * @return 返回注册数
     */
    public static int getRegisterCount() {
        return context.getRegisterCount();
    }
}
