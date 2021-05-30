package com.markben.common.utils;

import com.markben.common.logger.Logger;

import java.lang.reflect.Modifier;

/**
 * 加载工具类
 * @author 乌草坡
 * @since 0.0.1
 */
public class LoaderClassUtils {

    private static final Logger logger = LoggerUtils.getLogger(LoaderClassUtils.class);

    /**
     * 判断指定的类是否能加载
     * @param classPath 类路径
     * @param classLoader 类加载器
     * @return 如果支持加载则返回true；否则返回：false
     */
    public static boolean isCapableLoading(String classPath, ClassLoader classLoader) {
        if(StringUtils.isEmpty(classPath)) {
            return false;
        }
        if (null == classLoader) {
            classLoader = getDefaultClassLoader();
        }
        if (null == classLoader) {
            return false;
        }
        try {
            Class<?> clazz = classLoader.loadClass(classPath);
            if (null != clazz) {
                return true;
            }
        } catch (ClassNotFoundException e) {
        }
        return false;
    }

    /**
     * 获取默认的类加载器
     * @return 返回类加载器
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader classLoader = null;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
        }
        if (null == classLoader) {
            try {
                classLoader = LoaderClassUtils.class.getClassLoader();
            } catch (Throwable ex) {
            }
            if(null == classLoader) {
                classLoader = ClassLoader.getSystemClassLoader();
            }
        }
        return classLoader;
    }

    /**
     * 尝试着加载指定的类；
     * 如果加载成功返回对应的Class；否则返回：null
     * @param classPath 类路径
     * @param classLoader 类加载器
     * @return 如果加载成功返回对应的Class；否则返回：null
     */
    public static Class<?> tryLoadingClass(String classPath, ClassLoader classLoader) {
        if(StringUtils.isEmpty(classPath)) {
            return null;
        }
        if (null == classLoader) {
            classLoader = getDefaultClassLoader();
        }
        if (null == classLoader) {
            return null;
        }
        try {
            Class<?> clazz = classLoader.loadClass(classPath);
            if (null != clazz) {
                return clazz;
            }
        } catch (ClassNotFoundException e) {
        }
        return null;
    }

    /**
     * 尝试着加载指定的类并实例化；
     * 如果加载成功返回对应的Class；否则返回：null
     * @param classPath 类路径
     * @param classLoader 类加载器
     * @param <T> 加载类的实例
     * @return 如果加载成功返回对应的Class；否则返回：null
     */
    public static <T> T tryLoadingAndInstance(String classPath, ClassLoader classLoader) {
        Class<?> clazz = tryLoadingClass(classPath, classLoader);
        if(null == clazz) {
            return null;
        }
        if(clazz.isInterface()) {
            LoggerUtils.error(logger, "[{}]为接口类，无法实例化.", classPath);
            return null;
        }
        if(Modifier.isAbstract(clazz.getModifiers())) {
            LoggerUtils.error(logger, "[{}]为抽象类，无法实例化.", classPath);
            return null;
        }
        try {
            T t = (T) clazz.newInstance();
            return t;
        } catch (InstantiationException e) {
            LoggerUtils.error(logger,  e);
        } catch (IllegalAccessException e) {
            LoggerUtils.error(logger,  e);
        }
        return null;
    }
}
