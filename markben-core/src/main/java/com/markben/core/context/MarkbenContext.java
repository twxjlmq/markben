package com.markben.core.context;

import java.util.List;

/**
 * 服务查询接口
 * @author 乌草坡
 * @since 0.0.1
 */
public interface MarkbenContext {

	/**
	 * 根据服务名称，添加一个服务实例
	 * @param name 实例对象的名称
	 * @param obj 对象实例
	 */
	void put(String name, Object obj);

	/**
	 * 根据服务名称，添加一个服务实例
	 * @param obj 对象实例
	 */
	void put(Object obj);

	/**
	 * 根据服务名称，添加一个服务类(类型)
	 * @param name 实例对象的名称
	 * @param clazz 对象类
	 */
	void put(String name, Class<?> clazz);

	/**
	 * 根据给定名称判断是否存在服务
	 * @param name 实例对象的名称
	 * @return 存在返回true;否则返回false
	 */
	boolean isExist(String name);

	/**
	 * 根据给定的类型查找服务实例
	 * @param clazz 类 类型
	 * @return T 返回指定对象的实例
	 */
	<T> T find(Class<T> clazz);

	/**
	 * 根据给定的类型查找所有此类型的服务实例
	 * @param clazz 类 类型
	 * @return List<T> 返回指定类的实例集合
	 */
	<T> List<T> finds(Class<T> clazz);

	/**
	 *
	 * 根据给定的类型查找所有此类型的服务实例列表并排序
	 * @param clazz 类 类型
	 * @return Collection<T> 返回指定类的实例集合
	 */
	<T> List<T> findsAndOrder(Class<T> clazz);

	/**
	 * 根据给定的服务名称、类型查找服务实例
	 * @param name 实例对象的名称
	 * @param clazz 类类型
	 * @return T 返回指定类类型的实例
	 */
	<T> T findByName(String name, Class<T> clazz);
	
	/**
     * 根据名称获取服务实例
     * @param name 名称
     * @return 返回实现类
     */
    Object findByName(String name);

	/**
	 * 移除一个bean的定义
	 * @param name
	 */
	void removeBean(String name);

	/**
	 * 销毁一个Bean实例
	 * @param bean 实例
	 */
	void destroyBean(Object bean);

	/**
	 * 获取注册的数量
	 * @return 返回注册数
	 */
	int getRegisterCount();
}
