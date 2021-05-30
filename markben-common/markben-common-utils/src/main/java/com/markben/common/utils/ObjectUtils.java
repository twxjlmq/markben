package com.markben.common.utils;

import com.markben.common.logger.Logger;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

/**
 * 对象工具类
 * @author 乌草坡
 * @since 0.0.1
 */
public class ObjectUtils {

    private static Logger logger = LoggerUtils.getLogger(ObjectUtils.class);

    /**
     * 相同属性赋值的转换对象
     * @param convertedClazz 需要转换成的类类型
     * @param instance 需要转换的对象
     * @param <E> 类型
     * @return 返回对应类型的对象
     */
    public static <E> Optional<E> convertObject(Class<?> convertedClazz, Object instance) {
        if(null == convertedClazz || null == instance) {
            return Optional.empty();
        }
        try {
            E e = (E) convertedClazz.newInstance();
            setProperties(instance, e, PropertyUtils.getPropertyDescriptors(instance),
                    PropertyUtils.getPropertyDescriptors(convertedClazz));
            return Optional.of(e);
        } catch (IllegalAccessException | InstantiationException ex) {
            LoggerUtils.error(logger, ex);
        }
        return Optional.empty();
    }

    /**
     * 判断是否存在写的方法
     * @param targetPropertyDescriptors 目标类的属性描述数组
     * @param propertyDesc 属性描述对象
     * @return 如果存在返回true；否则返回false
     */
    private static boolean isHasWriteMethod(PropertyDescriptor[] targetPropertyDescriptors, PropertyDescriptor propertyDesc) {
        Method pdMethod = propertyDesc.getWriteMethod();
        if(null == pdMethod) {
            return false;
        }
        String writeMethodName = pdMethod.getName();
        return Arrays.stream(targetPropertyDescriptors).anyMatch(pd -> {
            Method targetMethod = pd.getWriteMethod();
            if(null == targetMethod) {
                return false;
            }
            return targetMethod.getName().equals(writeMethodName);
        });
    }

    /**
     * 重新赋值，赋值条件为：相同属性名称的赋值；可以是同一类型也可以不是；
     * 从<code>fromSource</code>参数对象中的属性值赋值到<code>toTarget</code>参数对象中的属性
     * @param fromSource 源对象（从哪个对象）
     * @param toTarget 目标对象（赋值到哪个对象）
     */
    public static void reassign(Object fromSource, Object toTarget) {
        if(null == fromSource || null == toTarget) {
            return;
        }
        if(fromSource == toTarget || fromSource.equals(toTarget)) {
            return;
        }
        setProperties(fromSource, toTarget, PropertyUtils.getPropertyDescriptors(fromSource),
                PropertyUtils.getPropertyDescriptors(toTarget));
    }

    /**
     * 设置属性值
     * @param fromSource 来源对象
     * @param toTarget 目标对象
     * @param sourcePropDescriptors 源对象属性描述数组
     * @param targetPropDescriptors 目标对象属性描述数组
     */
    private static void setProperties(Object fromSource, Object toTarget,
                                      PropertyDescriptor[] sourcePropDescriptors,
                                      PropertyDescriptor[] targetPropDescriptors) {
        if(null == sourcePropDescriptors) {
            return;
        }
        try {
            Arrays.stream(sourcePropDescriptors).filter(pd -> isHasWriteMethod(targetPropDescriptors, pd)).forEach(pd -> {
                try {
                    Method method = pd.getReadMethod();
                    if(null != method) {
                        BeanUtils.setProperty(toTarget, pd.getName(), method.invoke(fromSource));
                    }
                } catch(Exception ex){
                    LoggerUtils.error(logger, ex);
                }
            });
        } catch(Exception ex){
            LoggerUtils.error(logger, ex);
        }
    }

}
