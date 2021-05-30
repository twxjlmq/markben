package com.markben.common.utils;

import com.markben.common.constant.MarkbenConstant;

/**
 * 数组工具类
 * @author 乌草坡
 * @since 0.0.1
 */
public class ArrayUtils {

	private ArrayUtils() {
		throw new UnsupportedOperationException("ArrayUtils类无法实例化"); 
	}

	/**
	 * <p> 该方法已过时； 请使用 {@link StringUtils#arrayToString(Object[], String)} 方法代替. </p>
	 * 数组转化为字符串；
	 * 如果<code>separate</code>为空，则采用默认值；默认值为：{@link MarkbenConstant#MULTI_VALUE_SPLIT}
	 * @param objs 数组
	 * @param separate 分隔符
	 * @return 返回数组转化成功后的字符串;失败返回：null
	 */
	@Deprecated
	public static String arrayToString(Object[] objs,String separate) {
		StringBuilder strBuff = null;
		if(null == objs || objs.length == 0) {
			return null;
		}
		if(StringUtils.isEmpty(separate)) {
			separate = MarkbenConstant.MULTI_VALUE_SPLIT;
		}
		strBuff = new StringBuilder();
		for (int i=0;i<objs.length;i++) {
			if(i < objs.length-1 ) {
				strBuff.append(String.valueOf(objs[i])+separate);
			} else {
				strBuff.append(String.valueOf(objs[i]));
			}	
		}//for
		objs = null;
		return (null != strBuff)?strBuff.toString():null;
	}
	
	
	/**
	 * <p> 该方法已过时；请使用 {@link StringUtils#stringToArray(String, String)} 方法代替.</p>
	 * 字符串转化为数组；
	 * 如果<code>separate</code>为空，则采用默认值；默认值为：{@link MarkbenConstant#MULTI_VALUE_SPLIT}
	 * @param value 原字符串
	 * @param separate 分隔符
	 * @return 返回字符串分割成功后的数组
	 */
	@Deprecated
	public static String[] stringToArray(String value, String separate) {
		String[] array = null;
		if(StringUtils.isEmpty(separate)) {
			separate = MarkbenConstant.MULTI_VALUE_SPLIT;
		}
		if(StringUtils.isNotEmpty(value)) {
			array = value.split(separate);
		}
		value = null;
		return array;
	}
	
	
	/**
	 * <p> 该方法已过时；请使用 {@link StringUtils#isArrayContains(String, String, String)} 方法代替.</p>
	 * 按separate分离成数组,判断该数组里面是否包含subStr；
	 * 如果<code>separate</code>为空，则采用默认值；默认值为：{@link MarkbenConstant#MULTI_VALUE_SPLIT}
	 * @param str 字符串
	 * @param subStr 子字符串
	 * @param separate 分隔符
	 * @return 包含返回：true；否则返回：false
	 */
	@Deprecated
	public static boolean isArrayContains(String str,String subStr,String separate) {
		if(StringUtils.isEmpty(str)) {
			return StringUtils.isEmpty(subStr);
		}
		if(null == subStr) {
			return true;
		}
		boolean is = false;
		if(StringUtils.isEmpty(separate)) {
			separate = MarkbenConstant.MULTI_VALUE_SPLIT;
		}
		String[] strArray = str.split(separate);
		for (int i = 0; i < strArray.length; i++) {
			if(subStr.equals(strArray[i].trim())) {
				is = true;
				break;
			}
		}//for
		return is;
	}
	
	/**
	 * 判断该数组里面是否包含subObj
	 * @param objs 数组
	 * @param subObj 用于判断是否包含的元素；有可能是子元素或非子元素
	 * @return 包含返回：true；否则返回：false
	 */
	public static <E> boolean isArrayContains(E[] objs,E subObj) {
		if(null == subObj) {
			return true;
		}
		if(ArrayUtils.isEmpty(objs)) {
			return (subObj == null);
		}
		boolean is = false;
		for (Object obj : objs) {
			if(subObj.toString().equals(obj.toString())) {
				is = true;
				break;
			}
		}//for
		return is;
	}
	
	/**
	 * 判断该数组里面是否包含subObj；不区分大小写
	 * @param objs 数组
	 * @param subObj 用于判断是否包含的元素；有可能是子元素或非子元素
	 * @return 包含返回：true；否则返回：false
	 */
	public static <E> boolean isArrayContainsIgnoreCase(E[] objs,E subObj) {
		if(null == subObj) {
			return true;
		}
		if(ArrayUtils.isEmpty(objs)) {
			return (subObj == null);
		}
		boolean is = false;
		for (Object obj : objs) {
			if(subObj.toString().toLowerCase().equals(obj.toString().toLowerCase())) {
				is = true;
				break;
			}
		}//for
		return is;
	}
	
	/**
	 * 按<code>separate</code>分离成数组,
	 * 判断该数组里面是否包含<code>subStr</code>(不区分大小写) ；
	 * 如果<code>separate</code>为空，则采用默认值；默认值为：{@link MarkbenConstant#MULTI_VALUE_SPLIT}
	 * @param str 字符串
	 * @param subStr 用于判断是非包含的字符串；可能是子字符串或非子字符串
	 * @param separate 分隔符
	 * @return 包含返回：true；否则返回：false
	 */
	public static boolean isArrayContainsIgnoreCase(String str,String subStr,String separate) {
		if(StringUtils.isEmpty(str)) {
			return StringUtils.isEmpty(subStr);
		}
		if(StringUtils.isEmpty(subStr)) {
			return true;
		}
		if(StringUtils.isEmpty(separate)) {
			separate = MarkbenConstant.MULTI_VALUE_SPLIT;
		}
		boolean is = false;
		String[] strArray = str.split(separate);
		for (int i = 0; i < strArray.length; i++) {
			if(subStr.equalsIgnoreCase(strArray[i].trim())) {
				is = true;
				break;
			}
		}
		return is;
	}
	
	/**
	 * 判断是否为空；为空的条件是：
	 * （为null或者长度等于0）
	 * @param array 要判断的数组
	 * @return 如果为空，返回：true；否则返回：false
	 */
	public static <T> boolean isEmpty(T[] array) {
		return (null == array || array.length==0);
	}
	
	/**
	 * 判断是否不为空；不为空的条件是：
	 * （不为null并且长度大于0）
	 * @param array 要判断的数组
	 * @return 如果不为空，返回：true；否则返回：false
	 */
	public static <T> boolean isNotEmpty(T[] array) {
		return !isEmpty(array);
	}

	/**
	 * 获取<code>key</code>在数组中的位置；如果未找到则返回-1；否则返回所在位置的下标
	 * @param array 数组
	 * @param key 关键值
	 * @param <T> 类型
	 * @return 返回数组下标
	 */
	public static <T> int getArrayIndex(T[] array, T key) {
		if(null == array || array.length == 0 ||
				null == key || StringUtils.isEmpty(StringUtils.handleNull(key))) {
			return -1;
		}
		int index = -1;
		for(int i = 0; i < array.length; i++) {
			if(key.equals(array[i])) {
				index = i;
				break;
			}
		}
		return index;
	}
}
