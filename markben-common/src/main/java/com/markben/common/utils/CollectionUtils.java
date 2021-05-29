package com.markben.common.utils;

import com.markben.common.exception.NullArgumentException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 集合工具类
 * @author 乌草坡
 * @since 0.0.1
 */
public class CollectionUtils {

	private CollectionUtils() {
		throw new UnsupportedOperationException("CollectionUtils类无法实例化"); 
	}
	
	/**
	 * 判断 <code>objs</code> 集合中是否含有 <code>obj</code> 元素；
	 * 当<code>obj</code>元素为null，返回：false
	 * @param objs 集合对象
	 * @param obj 检查元素
	 * @return 包含返回：true；否则返回：false
	 */
	public static boolean isContain(Collection<?> objs, Object obj) {
		boolean is = false;
		if(isEmpty(objs))
			return (obj == null);
		if(null == obj) 
			return is;
		for (Object value : objs) {
			//检查元素集合中的元素类型和检测元素类型是否一致
			if(!obj.getClass().isAssignableFrom(value.getClass())) {
				is = false;
				break;
			}
			if(value.toString().equals(obj.toString())) {
				is = true;
				break;
			}
		}//for
		return is;
	}
	
	/**
	 * 判断列表是否为空
	 * @param objs 集合对象
	 * @return　如果列表为空，返回：true；否则返回：false
	 */
	public static boolean isEmpty(Collection<?> objs) {
		return (null == objs || objs.isEmpty());
	}
	
	/**
	 * 空列表转化为：null <br />
	 * @param objs 集合对象
	 * @return　返回列表；如果列表为空则返回null
	 */
	public static <E> List<E> handleNull(List<E> objs) {
		return (isEmpty(objs)?null:objs);
	}
	
	/**
	 * 判断列表是否不为空
	 * @param objs 集合对象
	 * @return　如果列表不为空，返回：true；否则返回：false
	 */
	public static boolean isNotEmpty(Collection<?> objs) {
		return !isEmpty(objs);
	}
	
	/**
	 * 对象集合转换为字符串集合(List)
	 * @param objs 集合对象
	 * @return 返回字符串集合
	 */
	public static List<String> toString(Collection<?> objs) {
		List<String> list = new ArrayList<String>();
		for (Object obj : objs) {
			list.add(obj.toString());
		}
		return list.size()>0?list:null;
	}
	
	/**
	 * 判断参数是否为空，如果为空，则抛参数为空的异常
	 * @param objs 集合对象
	 * @param msg 异常信息
	 */
	public static void isAssert(Collection<?> objs, String msg) {
	    msg = StringUtils.isEmpty(msg)?"参数为空":msg;
	    if(isEmpty(objs)) {
	        throw new NullArgumentException(msg);
	    }
	}
}
