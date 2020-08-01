package com.markben.core.context;

import java.util.List;

/**
 * 框架上下文工厂类
 * @autor 乌草坡
 * @since 1.0
 */
public class MarkbenContextFactory {

    private static IMarkbenContext context;

    public static IMarkbenContext getContext() {
        return context;
    }

    public static void setContext(IMarkbenContext context) {
        MarkbenContextFactory.context = context;
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
}
