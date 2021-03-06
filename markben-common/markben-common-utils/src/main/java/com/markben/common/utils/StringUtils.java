package com.markben.common.utils;

import com.markben.common.constant.MarkbenConstant;
import com.markben.common.exception.NullArgumentException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具类
 * @author 乌草坡
 * @since 0.0.1
 */
public class StringUtils {

	public static final String UNDERLINE = "_";

	public static final char UNDERLINE_CHAR = '_';

	public static final char DASHED_CHAR = '-';

	private StringUtils() {
		throw new UnsupportedOperationException("StringUtils类无法实例化"); 
	}
	
	/**
	 * 判断是否为空
	 * @param value 内容
	 * @return 为空返回：true；否则返回：false
	 */
	public static boolean isEmpty(String value) {
		return (null==value || value.trim().length()==0);
	}
	
	/**
	 * 判断是否不为空
	 * @param value　内容
	 * @return 不为空返回：true；否则返回：false
	 */
	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}

	/**
	 * 判断对象是否为空，或转换为字符串后的值是否为空
	 * @param value 需要判断的值
	 * @return 为空（null或“”）返回true；否则返回false
	 */
	public static boolean isEmpty(Object value) {
		if(null == value) {
			return true;
		} else {
			return isEmpty(handleNull(value));
		}
	}

	/**
	 * 判断对象是否不为空；
	 * @param value 需要判断的值
	 * @return 如果不为空返回true；否则返回false
	 */
	public static boolean isNotEmpty(Object value) {
		return !isEmpty(value);
	}

	/**
	 * 判断参数；如果参数<code>value</code> 为空；
	 * 则抛出参数为空异常（运行时异常）
	 * @param value 需要判断的参数
	 * @param msg 提示信息
	 */
	public static void isAssert(Object value, String msg) {
		isAssert(value, msg, null);
	}

	/**
	 * 判断参数；如果参数<code>value</code> 为空；
	 * 则抛出参数为空异常（运行时异常）
	 * @param value 需要判断的参数
	 * @param msg 提示信息
	 * @param obj 对象
	 */
	public static void isAssert(Object value, String msg, Object obj) {
		msg = isEmpty(msg)?"提供的参数为空":msg;

		boolean isException = false;
		if(null == value) {
			isException = true;
		} else if(value instanceof String) {
			if(isEmpty(value.toString())) {
				isException = true;
			}
		} else if(value instanceof Collection) {
			if(CollectionUtils.isEmpty((Collection<?>)value)) {
				isException = true;
			}
		} else if(value.getClass().isArray()) {
			if(ArrayUtils.isEmpty((Object[])value)) {
				isException = true;
			}
		}
		if(isException) {
			if(null != obj) {
				msg = msg  + MarkbenConstant.EXCEPTION_INDICATOR + obj.getClass().getName();
			}
			throw new NullArgumentException(msg);
		}
	}
	
	/**
	 * 判断值是否相等
	 * @param value1 第一个值
	 * @param value2 第二个值
	 * @return 相等返回：true；否则返回：false
	 */
	public static boolean isEquals(Object value1, Object value2) {
		boolean is = false;
		if(null != value1 && null != value2) {
			is = value1.toString().equals(value2.toString());
		} else if(null == value1 && null == value2) {
			is = true;
		}
		return is;
	}
	
	/**
	 * 判断值是否不相等
	 * @param value1 第一个值
	 * @param value2 第二个值
	 * @return 相等返回：true；否则返回：false
	 */
	public static boolean isNotEquals(Object value1, Object value2) {
		return !isEquals(value1, value2);
	}
	
	/**
	 * 判断值是否相等（不区分大小写）
	 * @param value1 第一个值
	 * @param value2 第二个值
	 * @return 相等返回：true；否则返回：false
	 */
	public static boolean isEqualsIgnoreCase(Object value1, Object value2) {
		boolean is = false;
		if(null != value1 && null != value2) {
			is = value1.toString().equalsIgnoreCase(value2.toString());
		} else if(null == value1 && null == value2) {
			is = true;
		}
		return is;
	}
	
	/**
	 * 判断值是否不相等（不区分大小写）
	 * @param value1 第一个值
	 * @param value2 第二个值
	 * @return 相等返回：true；否则返回：false
	 */
	public static boolean isNotEqualsIgnoreCase(Object value1, Object value2) {
		return !isEqualsIgnoreCase(value1, value2);
	}
	
	/**
	 * null转换为“”
	 * @param obj 需要处理的值
	 * @return 返回处理后的结果
	 */
	public static String handleNull(Object obj) {
		if (null == obj) {
			return "";
		} else {
			return obj.toString().trim();
		}
	}
	
	/**
	 * 当值为null转化为“null”
	 * @param obj 需要处理的值
	 * @return 返回处理后的结果
	 */
	public static String nullToStr(Object obj) {
		if (null == obj) {
			return "null";
		} else {
			return obj.toString().trim();
		}
	}
	
	/**
	 * 对象转化为整型
	 * @param obj 需要处理的值
	 * @return 返回转化结果
	 */
	public static Integer handleObj2Integer(Object obj) {
		if (null == obj) {
			return 0;
		} else {
			Integer value = 0;
			try {
			   value = Integer.parseInt(obj.toString());
			} catch (Exception ex) {
				value = 0;
			}
			return value;
		}
	}
	
	/**
	 * 数字null转换为“0”
	 * @param obj 需要处理的值
	 * @return 返回转化结果
	 */
	public static String handleNumNull(Object obj) {
		if (null == obj) {
			return "0";
		} else {
			return obj.toString().trim();
		}
	}
	
	/**
	 * 判断是否为数字(包括小数)
	 * @param value 需要处理的值
	 * @return 数字返回：true；否则返回：false
	 */
	public static boolean isNum(String value) {
		Pattern pattern = Pattern.compile("\\d+|\\d+\\.\\d+");
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}
	
	
	/**
	 * 判断是否数字整数
	 * @param value 需要处理的值
	 * @return 是返回：true；否则返回：false
	 */
	public static boolean isInteger(String value) {
		Pattern pattern = Pattern.compile("\\d+");
		if(null != value && value.length()>1) {
			pattern = Pattern.compile("^[1-9]\\d+");
		}
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	} 
	
	/**
	 * 判断是否小数
	 * @param value 需要处理的值
	 * @return 是返回：true；否则返回：false
	 */
	public static boolean isDecimal(String value) {
		Pattern pattern = Pattern.compile("\\d+\\.\\d+");
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	} 
	
	
	/**
	 * 随机生成数
	 * @param num 要生成随机数的个数
	 * @return 返回随机生成数
	 */
	public static String randomNum(int num) {
		Random random = new Random();
		StringBuilder numStr = new StringBuilder();
		for (int i = 0; i < num; i++) {
			numStr.append(random.nextInt(10));
		}
		return numStr.toString();
	}
	
	
	/**
	 * 按日期格式生成序列号
	 * @param dateFormatStr 日期格式
	 * @return 返回序列号
	 */
	public static String createSerialNum(String dateFormatStr) {
		String serialNum = null;
		if(!isEmpty(dateFormatStr)) {
			SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormatStr);
			serialNum = dateFormatter.format(new Date());
		}
		return serialNum;
	}
	
	
	/**
	 * 生成序列号；
	 * TODO 改为采用UUID生成序列号
	 * 生成的序列号；
	 * @return 返回系统自动生成的序列号
	 */
	public static String createSerialNum() {
		/*long time = System.currentTimeMillis();
		return "U"+time+randomNum(5);*/
	    return uuid();
	}
	
	/**
	 * 生成UUID序列号
	 * @return 返回UUID
	 */
	public static String uuid() {
		String value = UUID.randomUUID().toString();
		value = value.replaceAll("-", "");
		return value;
	}
	
	/**
	 * 获取文件后缀
	 * @param fileName 文件名称
	 * @return 返回文件后缀
	 */
	public static String getFileSuffix(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".")+1);
	}
	
	
	/**
	 * 去掉文件后缀
	 * @param fileName 文件名称
	 * @return 返回文件后缀
	 */
	public static String trimFileSuffix(String fileName) {
		return fileName.substring(0,fileName.lastIndexOf("."));
	}
	
	
	/**
	 * 大写字母直接用下划线分割，并把大写转换为小写；
	 * 如:HelloWorld 转换为： hello_world
	 * @param value 需要转换的值
	 * @return 返回处理结果
	 */
	public static String upperSeparateUnderline(String value) {
		StringBuilder strBuilder = null;
		if(!isEmpty(value)) {
		    strBuilder = new StringBuilder();
			byte[] values = value.getBytes();
			for (int i = 0; i < values.length; i++) {
				if(values[i]>=65 && values[i]<=90) {
					if(i>0 && i<(values.length-1)) {
					    strBuilder.append("_"+(char)((int)values[i]+32));
					} else {
					    strBuilder.append((char) ((int) values[i] + 32));
					}
				} else {
				    strBuilder.append((char) values[i]);
				}
			}
		}
		return strBuilder != null ? strBuilder.toString() : null;
	}
	
	/**
	 * 首字母转为大写
	 * @param value 需要处理的值
	 * @return 返回处理后的结果
	 */
	public static String firstToUppercase(String value) {
		if(!StringUtils.isEmpty(value)) {
			String firstChar = value.substring(0, 1);
			String otherChar = value.substring(1);
			firstChar = firstChar.toUpperCase();
			value = firstChar+otherChar;
		}
		return value;
	}
	
	
	/**
	 * 过滤HTML标签.
	 * @param htmlContent 需要处理的值
	 * @return 返回过滤后的结果
	 */
	public static String html2Text(String htmlContent) {
		if(isEmpty(htmlContent))
			return htmlContent;
		Pattern pScript, pStyle, pHtml; //定义规则
	    Matcher mScript, mStyle, mHtml; //匹配规则
	    
	    //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
        String regExScript = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; 
        //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
        String regExStyle = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
         //定义HTML标签的正则表达式
        String regExHtml = "<[^>]+>";
        
        //过滤script标签
        pScript = Pattern.compile(regExScript, Pattern.CASE_INSENSITIVE);
        mScript = pScript.matcher(htmlContent);
        htmlContent = mScript.replaceAll(""); 

        //过滤style标签
        pStyle = Pattern.compile(regExStyle, Pattern.CASE_INSENSITIVE);
        mStyle = pStyle.matcher(htmlContent);
        htmlContent = mStyle.replaceAll(""); 

        //过滤html标签
        pHtml = Pattern.compile(regExHtml, Pattern.CASE_INSENSITIVE);
        mHtml = pHtml.matcher(htmlContent);
        htmlContent = mHtml.replaceAll("");
	    
		return htmlContent;
	}
	
	
	/**
	 * 验证手机号码
	 * @param mobileNo 手机号
	 * @return 验证成功返回：true；否则返回：false
	 */
	public static boolean checkMobileNo(String mobileNo){
		Pattern p = Pattern.compile("^1(3\\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$");
		Matcher m = p.matcher(mobileNo);
		return m.matches();
	}
	
	
	/**
	 * 验证固定电话号码
	 * @param tel 需要验证的电话号码
	 * @return 验证成功返回：true；否则返回：false
	 */
	public static boolean checkFixedTelephone(String tel) {
		Pattern p = Pattern.compile("^[0][0-9]{2,3}-[2-9][0-9]{6,7}(-[0-9]{1,4})?");
		Matcher m = p.matcher(tel);
		return m.matches();
	}
	
	
	/**
	 * 验证匿名
	 * @param anonymous 匿名
	 * @return 验证成功返回：true；否则返回：false
	 */
	public static boolean checkAnonymous(String anonymous){
		Pattern p = Pattern.compile("^[\\u4e00-\\u9fa5|A-Za-z]([\\w|\\u4e00-\\u9fa5]){1,7}$");
		Matcher m = p.matcher(anonymous);
		return m.matches();
	}
	
	/**
	 * 验证汉字
	 * @param value 需要处理的值
	 * @return 验证成功返回：true；否则返回：false
	 */
	public static boolean checkChinese(String value){
		Pattern p = Pattern.compile("^[\\u4E00-\\u9FFF]+$");
		Matcher m = p.matcher(value);
		return m.matches();
	}
	
	/**
	 * 验证正则表达式
	 * @param value 需要处理的值
	 * @param regex 正则表达式
	 * @return 验证成功返回：true；否则返回：false
	 */
	public static boolean checkCheckRegex(String value,String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);
		return m.matches();
	}
	
	/**
	 * 验证email
	 * @param email 需要处理的值
	 * @return 验证成功返回：true；否则返回：false
	 */
	public static boolean checkEmail(String email) {
		Pattern p = Pattern.compile("^([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$");
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
	/**
	 * 秒转化为:HH:mm:SS格式
	 * @param second 秒数
	 * @return 返回转化后的结果
	 */
	public static String secondToHHMMSS(long second){
		long h = 0, m = 0, s = 0, tmp = 0;
		if(second >= 3600){
		   h= second / 3600;
		   tmp = second % 36000;
		   if(tmp >= 60) {
			   m = tmp / 60;
			   s = tmp % 60;
		   }else {
		     s = tmp;
		   }
		} else if(second >= 60) {
		   m = second / 60;
		   s =  second % 60;
		} else {
		   s = second;
		}
		return (h > 9 ? h : "0" + h) + ":" + (m > 9 ? m : "0" + m) + ":" + (s > 9 ? s : "0" + s);
	 }
	
	
	/**
	 * 过滤特殊字符
	 * @param params 参数
	 * @return 返回过滤后的结果
	 */
	public static String filterSQLParams(String params) {
		if(!isEmpty(params)) {
			String strBuilder = "'|\"|update|delete|select|drop|insert|=|;|0x|\\(|\\)|\\s|\\*|\\?|\\%|\\$" +
					"|and|exec|execute|chr|mid|master|truncate|char|declare|sitename|net user|xp_cmdshell|or" +
					"|\\+|,|like'|table|from|grant|use|group_concat|column_name|information_schema.columns" +
					"|table_schema|union|where|order|by|count" +
					"|--|,|like|//|/|#";
		    String params1 = params.toLowerCase();
		    params1 = params1.replaceAll(strBuilder, "");
		    if("".equals(params1)&&!"''".equals(params)){
		    	params = params1;
		    }
			params = params.replaceAll("&", "&amp");
			params = params.replaceAll("<", "&lt");
			params = params.replaceAll(">", "&gt");	
		}
		return params;
	}

	/**
	 * 计算出文件大小
	 * @param size 文件大小
	 * @return 返回处理后的结果
	 * 格式为："100 KB"或”100 M“或”100 G“
	 */
	public static String fileSize(long size) {
		DecimalFormat df = new DecimalFormat("0.0#");
		long KB = 1024;
		long MB = KB * 1024;
		long GB = MB * 1024;
		String valueStr = null;
		if(size < 0) {
			valueStr = "0 KB";
		} else if(size < KB * 1024) {
			double value = (double)size/KB;
			valueStr = df.format(value)+" KB";
		} else if(size < MB * 1024) {
			double value = (double)size/MB;
			valueStr = df.format(value)+" M";
		} else {
			double value = (double)size/GB;
			valueStr = df.format(value)+" G";
		}
		return valueStr;
	}
	
	
	/**
	 * 过滤目录结构(防止参数传递目录结构)
	 * @param value 需要处理的值
	 * @return 返回过滤后的结果
	 */
	public static String filterFilePath(String value) {
		if(!StringUtils.isEmpty(value)) {
			value.replaceAll("\\.|/|\\\\\\\\|\\\\|:|%2F|%2E|25%|20%|%5C|60%|27%|%3A|%2A","");
		}
	    return value;
	}
	
	/**
	 * 判断是否含有<code>contain</code>指定的值
	 * @param value 需要处理的值
	 * @param contain 指定需要判断的值
	 * @return 包含返回：true；否则返回：false
	 */
	public static boolean isContains(String value, String contain) {
		boolean is = false;
		if(!isEmpty(value) && null != contain) {
			is = value.contains(contain);
		}
		return is;
	}
	
	
	/**
	 * list转换为数组
	 * @param values 需要处理的集合
	 * @return 返回转化结果
	 */
	public static String[] list2Array(Collection<String> values) {
		String[] valueArray = null;
		if(null != values && values.size()>0) {
			valueArray = new String[values.size()];
			values.toArray(valueArray);
		}
		return valueArray;
	}
	
	/**
	 * 验证IP地址
	 * @param ip 需要处理的值
	 * @return 验证成功返回：true；否则返回：false
	 */
	public static boolean checkIp(String ip) {
		boolean is = false;
		String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(ip);
		if(matcher.matches()) {
			is = true;
		}
		return is;
	}
	
	
	/**
	 * 集合转换为字符串，字符串之间用separator参数提供的参数分隔
	 * @param values 需要处理的集合
	 * @param separator 分隔符
	 * @param <T> 集合类类型
	 * @return 返回处理后的结果
	 */
	public static <T> String collection2String(Collection<T> values,String separator) {
	    StringBuilder strBuilder = null;
		if(null != values && values.size()>0) {
		    strBuilder = new StringBuilder();
			if(StringUtils.isEmpty(separator)) {
				separator = "";
			}
			for (T value : values) {
			    strBuilder.append(StringUtils.handleNull(value) + separator);
			}
			if(!StringUtils.isEmpty(separator)) {
			    strBuilder.delete(strBuilder.length()-1, strBuilder.length());
			}
		}
		return strBuilder != null ? strBuilder.toString():null;
	}
	
	
	/**
	 * 替换多个空格、换行、回车、tab符等为一个空格
	 * @param value 预处理的字符串
	 * @return 返回处理后的字符串
	 */
	public static String removeMultiSpace(String value) {
		if(StringUtils.isNotEmpty(value)) {
			value = value.replaceAll("\\s+|\n|\r|\n|\t", " ");
		}
		return value;
	}
	
	/**
	 * 替换特殊字符
	 * @param value 预处理的字符串
	 * @return 返回处理后的结果
	 */
	public static String replaceSpecialChar(String value) {
		if(isNotEmpty(value)) {
			value = value.replaceAll("\r|\n|\t", "");
		}
		return value;
	}
	
	/**
	 * 替换斜杠
	 * @param value 需要处理的值
	 * @return 返回处理后的结果
	 */
	public static String replaceSlash(String value) {
		if(isNotEmpty(value)) {
			//value = value.replaceAll("\\\\", "\\\\\\\\\\\\\\\\");
			value = value.replaceAll("\\\\", "\\\\\\\\");
		}
		return value;
	}
	
	/**
	 * 字符串转换为list
	 * @param value 要转换的字符串
	 * @param separator 分隔符
	 * @return 返回转换后的List
	 */
	public static List<String> string2List(String value, String separator) {
		List<String> lists = null;
		if(isNotEmpty(value)) {
			separator = isEmpty(separator) ? MarkbenConstant.MULTI_VALUE_SPLIT : separator;
			String[] array = value.split(separator);
			lists = Arrays.asList(array);
		}
		return lists;
	}
	
	/**
     * 字符串转换为Set
     * @param value 要转换的字符串
     * @param separator 分隔符
     * @return 返回转换后的Set
     */
    public static Set<String> string2Set(String value, String separator) {
        Set<String> sets = null;
        if(isNotEmpty(value)) {
			separator = isEmpty(separator) ? MarkbenConstant.MULTI_VALUE_SPLIT : separator;
            String[] array = value.split(separator);
            sets = new HashSet<String>();
            sets.addAll(Arrays.asList(array));
        }
        return sets;
    }
	
	/**
	 * 处理URL参数；
	 * 判断 url 参数中是否有"?"；
	 * 如果有则会在url参数后面加"&"，没有则加"?"
	 * @param url URL字符串
	 * @return 返回处理后的URL
	 */
	public static String handleUrlParam(String url) {
	    if(isEmpty(url)) {
	        return url;
	    }
	    StringBuilder urlBuilder = new StringBuilder(url);
	    if(!url.contains("?")) {
	        urlBuilder.append("?");
	    } else {
	        urlBuilder.append("&");
	    }
	    return urlBuilder.toString();
	}

	/**
	 * 处理路径；判断路径后是否有"/"；如果没有则加上，如果有则不处理
	 * @param path 路径
	 * @return 返回处理后的路径
	 */
	public static String handlePath(String path) {
		if(isEmpty(path)) {
			return path;
		}
		if(!path.endsWith("/")) {
			path = path + "/";
		}
		return path;
	}
	
	/**
	 * 如果值为空“”转换为NULL
	 * @param value 需要处理的值
	 * @return 返回处理后的值
	 */
	public static String empty2Null(String value) {
	    return isEmpty(value) ? null : value;
	}
	
	/**
	 * 移除多值，只获取第一个值；多值之间通过英文逗号分隔
	 * @param value 需要处理的值
	 * @return 返回处理后的值
	 */
	public static String removeMultiValue(String value) {
	    if(isEmpty(value)) {
	        return value;
	    }
	    int p = value.indexOf(MarkbenConstant.MULTI_VALUE_SPLIT);
	    if(p >= 0) {
	        value = value.substring(0, p);
	    }
	    return value;   
	}

	/**
	 * 数组转化为字符串；
	 * 如果参数separate为空，则采用默认值；默认值为：{@link MarkbenConstant#MULTI_VALUE_SPLIT}
	 * @param objs 数组
	 * @param separate 分隔符
	 * @return 返回数组转化成功后的字符串;失败返回：null
	 */
	public static String arrayToString(Object[] objs,String separate) {
		if(null == objs || objs.length == 0) {
			return null;
		}
		if(StringUtils.isEmpty(separate)) {
			separate = MarkbenConstant.MULTI_VALUE_SPLIT;
		}
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < objs.length; i++) {
			if(i < objs.length-1 ) {
				strBuilder.append(objs[i] + separate);
			} else {
				strBuilder.append(objs[i]);
			}
		}//for
		return (null != strBuilder)?strBuilder.toString():null;
	}

	/**
	 * 数组转化为字符串；使用默认分隔符，值为：{@link MarkbenConstant#MULTI_VALUE_SPLIT}
	 * @param objs 数组
	 * @return 返回数组转化成功后的字符串;失败返回：null
	 */
	public static String arrayToString(Object[] objs) {
		return arrayToString(objs, null);
	}


	/**
	 * 字符串转化为数组；
	 * 如果separate参数为空，则采用默认值；默认值为：{@link MarkbenConstant#MULTI_VALUE_SPLIT}
	 * @param value 原字符串
	 * @param separate 分隔符
	 * @return 返回字符串分割成功后的数组
	 */
	public static String[] stringToArray(String value, String separate) {
		String[] array = null;
		if(StringUtils.isEmpty(separate)) {
			separate = MarkbenConstant.MULTI_VALUE_SPLIT;
		}
		if(StringUtils.isNotEmpty(value)) {
			array = value.split(separate);
		}
		return array;
	}

	/**
	 * 字符串转化为数组；使用默认分隔符，值为：{@link MarkbenConstant#MULTI_VALUE_SPLIT}
	 * @param value 原字符串
	 * @return 返回字符串分割成功后的数组
	 */
	public static String[] stringToArray(String value) {
		return stringToArray(value, null);
	}


	/**
	 * 按separate分离成数组,判断该数组里面是否包含subStr；
	 * 如果separate参数为空，则采用默认值；默认值为：{@link MarkbenConstant#MULTI_VALUE_SPLIT}
	 * @param str 字符串
	 * @param subStr 子字符串
	 * @param separate 分隔符
	 * @return 包含返回：true；否则返回：false
	 */
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
	 * 填补位数；如果fillText参数为空，则默认填充内容为“0”
	 * @param value 需要填补的值
	 * @param num 需要填补的位数
	 * @param fillText 填充内容
	 * @return 返回处理后的值
	 */
	public static String supplementDigit(String value, int num, String fillText) {
		if(StringUtils.isEmpty(value)) {
			return value;
		}
		if(value.length() >= num) {
			return value;
		}
		if(StringUtils.isEmpty(fillText)) {
			fillText = "0";
		}
		StringBuilder stringBuilder = new StringBuilder();
		int fillNum = num - value.length();
		for(int i = 0; i < fillNum; i++) {
			stringBuilder.append(fillText);
		}
		stringBuilder.append(value);
		return stringBuilder.toString();
	}

	/**
	 * 转换为Long
	 * @param value 需要转换的值
	 * @return 返回转换后的值；如果value参数值为null；则返回0
	 */
	public static Long convertLong(Object value) {
		String valueStr = null;
		if(null == value) {
			valueStr = "0";
		} else {
			valueStr = value.toString();
		}
		long num = 0;
		try {
			num = Long.parseLong(valueStr);
		} catch (Exception ex) {
			num = 0;
		}
		return num;
	}

	/**
	 * 转换为Integer
	 * @param value 需要转换的值
	 * @return 返回转换后的值；如果value参数值为null；则返回0
	 */
	public static Integer convertInteger(Object value) {
		String valueStr = null;
		if(null == value) {
			valueStr = "0";
		} else {
			valueStr = value.toString();
		}
		int num = 0;
		try {
			num = Integer.parseInt(valueStr);
		} catch (Exception ex) {
			num = 0;
		}
		return num;
	}

	/**
	 * 转换为Boolean
	 * @param value 需要转换的值
	 * @return 返回转换后的值; 如果value参数值为null；则返回false
	 */
	public static Boolean convertBoolean(Object value) {
		if(null == value) {
			return false;
		}
		boolean is = Boolean.FALSE;
		try {
			is = Boolean.parseBoolean(value.toString());
		} catch (Exception ex) {
			is = Boolean.FALSE;
		}
		return is;
	}


	/**
	 * 将驼峰式命名的字符串转换为下划线方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。<br>
	 * 例如：
	 * <pre>
	 * HelloWorld 转换为： hello_world
	 * Hello_World 转换为： hello_world
	 * HelloWorld_test 转换为： hello_world_test
	 * </pre>
	 * @param str 转换前的驼峰式命名的字符串，也可以为下划线形式
	 * @return 转换后下划线方式命名的字符串
	 */
	public static String toUnderlineCase(CharSequence str) {
		return toSymbolCase(str, UNDERLINE_CHAR);
	}

	/**
	 * 将驼峰式命名的字符串转换为使用符号连接方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。<br>
	 *
	 * @param str  转换前的驼峰式命名的字符串，也可以为符号连接形式
	 * @param symbol 连接符
	 * @return 转换后符号连接方式命名的字符串
	 */
	public static String toSymbolCase(CharSequence str, char symbol) {
		if (str == null) {
			return null;
		}

		final int length = str.length();
		final StringBuilder sb = new StringBuilder();
		char c;
		for (int i = 0; i < length; i++) {
			c = str.charAt(i);
			final Character preChar = (i > 0) ? str.charAt(i - 1) : null;
			if (Character.isUpperCase(c)) {
				// 遇到大写字母处理
				final Character nextChar = (i < str.length() - 1) ? str.charAt(i + 1) : null;
				if (null != preChar && Character.isUpperCase(preChar)) {
					// 前一个字符为大写，则按照一个词对待
					sb.append(c);
				} else if (null != nextChar && Character.isUpperCase(nextChar)) {
					// 后一个为大写字母，按照一个词对待
					if (null != preChar && symbol != preChar) {
						// 前一个是非大写时按照新词对待，加连接符
						sb.append(symbol);
					}
					sb.append(c);
				} else {
					// 前后都为非大写按照新词对待
					if (null != preChar && symbol != preChar) {
						// 前一个非连接符，补充连接符
						sb.append(symbol);
					}
					sb.append(Character.toLowerCase(c));
				}
			} else {
				if (sb.length() > 0 && Character.isUpperCase(sb.charAt(sb.length() - 1)) && symbol != c) {
					// 当结果中前一个字母为大写，当前为小写，说明此字符为新词开始（连接符也表示新词）
					sb.append(symbol);
				}
				// 小写或符号
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 将下划线方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。<br>
	 * 例如：hello_world 转换为：helloWorld
	 * @param name 转换前的下划线大写方式命名的字符串
	 * @return 转换后的驼峰式命名的字符串
	 */
	public static String toCamelCase(CharSequence name) {
		if (null == name) {
			return null;
		}
		String handleName = name.toString();
		if (handleName.contains(UNDERLINE)) {
			final StringBuilder sb = new StringBuilder(handleName.length());
			boolean upperCase = false;
			for (int i = 0; i < handleName.length(); i++) {
				char c = handleName.charAt(i);
				if (c == UNDERLINE_CHAR) {
					upperCase = true;
				} else if (upperCase) {
					sb.append(Character.toUpperCase(c));
					upperCase = false;
				} else {
					sb.append(Character.toLowerCase(c));
				}
			}
			return sb.toString();
		} else {
			return handleName;
		}
	}

	/**
	 * 移除ID的前缀；注：前缀是通过下划线分隔标记的；
	 * 如:ps_11111；移除前缀后为：1111
	 * @param id 需要处理的值
	 * @return 返回移除前缀后的值
	 */
	public static String removeIdPrefix(String id) {
		if(isEmpty(id)) {
			return id;
		}
		int index = id.indexOf(MarkbenConstant.COMBINE_VALUE_SEPARATOR);
		if(index > -1) {
			return id.substring(index + 1);
		}
		return id;
	}

	/**
	 * 提取名称，从指定的字符串中；
	 * 如内容为：我的姓名为：${name}，出生于${date}，现居住在${address}.
	 * <code>
	 *     extractName("我的姓名为：${name}，出生于${date}，现居住在${address}", "${", "}");
	 *     提取变量名后，返回列表；结果为：["name", "date", "address"]
	 * </code>
	 * @param value 需要提取的内容
	 * @param prefix 需要提取名称的前缀；如果为空，则使用默认值：“${”
	 * @param suffix 需要提取名称的后缀；如果为空，则使用默认值：“}”
	 * @return 返回提取后的名称列表；如果value为空或者未找到提取的名称，则返回空的列表
	 */
	public static List<String> extractName(String value, String prefix, String suffix) {
		if(StringUtils.isEmpty(value)) {
			return Collections.EMPTY_LIST;
		}
		if(StringUtils.isEmpty(prefix)) {
			prefix = "${";
		}
		if(StringUtils.isEmpty(suffix)) {
			suffix = "}";
		}
		int index = value.indexOf(prefix);
		if(index == -1) {
			return Collections.EMPTY_LIST;
		}
		int suffixIndex = value.indexOf(suffix);
		if(suffixIndex == -1) {
			return Collections.EMPTY_LIST;
		}
		List<String> names = new ArrayList<>();
		int prefixLen = prefix.length();
		while(index > -1 && suffixIndex > -1) {
			String name = value.substring(index + prefixLen, suffixIndex);
			if(StringUtils.isNotEmpty(name)) {
				names.add(name);
			}
			index = value.indexOf(prefix, suffixIndex);
			if(index > -1) {
				suffixIndex = value.indexOf(suffix, index);
			}
		}
		return names;
	}

	/**
	 * Map对象转换为字符串分隔的字符串
	 * @param values map对象
	 * @param separator 分隔符
	 * @return 返回处理后的字符串
	 */
	public static String map2String(Map<String, Object> values, String separator) {
		if(StringUtils.isEmpty(separator)) {
			separator = MarkbenConstant.MULTI_VALUE_SPLIT;
		}
		if(null == values || values.isEmpty()) {
			return "";
		}
		StringBuilder stringBuilder = new StringBuilder();
		Set<Map.Entry<String, Object>> sets = values.entrySet();
		for(Map.Entry<String, Object> entry : sets) {
			stringBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append(separator);
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		return stringBuilder.toString();
	}

	/**
	 * Map对象转换为字符串分隔的字符串
	 * @param values map对象
	 * @return 返回处理后的字符串
	 */
	public static String map2String(Map<String, Object> values) {
		return map2String(values, null);
	}

	/**
	 * 获取异常信息
	 * @param ex 异常对象
	 * @return 返回异常信息
	 */
	public static String getExceptionMsg(Exception ex) {
		String msg = ex.getMessage();
		if(StringUtils.isEmpty(msg)) {
			msg = getExceptionStackTrace(ex);
		}
		return msg;
	}

	/**
	 * 获取异常栈信息
	 * @param ex 异常对象
	 * @return 返回异常信息
	 */
	public static String getExceptionStackTrace(Exception ex) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		try {
			ex.printStackTrace(writer);
		} finally {
			writer.close();
		}
		return stringWriter.toString();
	}

}
