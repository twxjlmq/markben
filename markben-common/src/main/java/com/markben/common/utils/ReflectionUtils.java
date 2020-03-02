package com.markben.common.utils;

import com.markben.common.logger.ILogger;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 反射工具类
 * @author 乌草坡
 * @since 1.0
 */
public class ReflectionUtils {

	public static final ILogger logger = LoggerUtils.getLogger(ReflectionUtils.class);
	
	private ReflectionUtils() {
		throw new UnsupportedOperationException("ReflectionUtils类无法实例化"); 
	}
	/**
	 * 通过反射, 获得Class定义中声明的父类的泛型参数的类型.
	 * 如无法找到, 返回Object.class.
	 * 如：public UserDao extends BaseDao<User>
	 * 
	 * @param clazz 
	 * @return 返回父类的泛型参数的类型, 如果不能确定则返回Object.class
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getSuperClassGenricType(final Class<?> clazz) {
		return (Class<T>) getSuperClassGenricType(clazz, 0);
	}
	
	/**
	 * 通过反射, 获得Class定义中声明的父类的泛型参数的类型. <br />
	 * 如无法找到, 返回Object.class.<br />
	 * 如public UserDao extends BaseDao<User,Long>
	 * 
	 * @param clazz 泛型声明的索引，从0开始.
	 * @param index 泛型声明的索引，从0开始.
	 * @return 返回父类的泛型参数的类型, 如果不能确定则返回Object.class
	 */
	public static Class<?> getSuperClassGenricType(final Class<?> clazz, final int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			LoggerUtils.warn(logger, "[{}]父类不是ParameterizedType类型.", clazz.getSimpleName());
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			LoggerUtils.warn(logger, "[{}]不是一个实际的父类泛型参数.", clazz.getSimpleName());
			return Object.class;
		}
		return (Class<?>) params[index];
	}
	
	/**
	 * 使用反射技术得到泛型的真实类型
	 * @return
	 */
    public static Class<?> getRealType(final Class<?> clazz){
        // 获取当前new的对象的泛型的父类类型
        ParameterizedType pt = (ParameterizedType) clazz.getGenericSuperclass();
        // 获取第一个类型参数的真实类型
        return (Class<?>) pt.getActualTypeArguments()[0];
    }
}
