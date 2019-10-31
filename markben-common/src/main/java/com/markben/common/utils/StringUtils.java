package com.markben.common.utils;

import com.markben.common.constant.MarkbenConstant;
import com.markben.common.exception.NullArgumentException;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理工具类
 * @author 乌草坡
 * @since 1.0
 */
public class StringUtils {

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
	 * 判断参数；如果参数<code>value</code> 为空；
	 * 则抛出参数为空异常（运行时异常）
	 * @param value 需要判断的参数
	 * @param msg 提示信息
	 */
	public static void isAssert(Object value, String msg) {
		msg = isEmpty(msg)?"提供的参数为空":msg;
		if(null == value) {
			throw new NullArgumentException(msg);
		}
		if(value instanceof String) {
			if(isEmpty(value.toString())) {
				throw new NullArgumentException(msg);
			}
		} else if(value instanceof Collection) {
		    if(CollectionUtils.isEmpty((Collection<?>)value)) {
		        throw new NullArgumentException(msg);
		    }
		} else if(value.getClass().isArray()) {
		    if(ArrayUtils.isEmpty((Object[])value)) {
		        throw new NullArgumentException(msg);
		    }
		}
	}
	
	/**
	 * 判断值是否相等
	 * @param value1
	 * @param value2
	 * @return 相等返回：true；否则返回：false
	 */
	public static boolean isEquals(Object value1, Object value2) {
		boolean is = false;
		if(null != value1 && null != value1) {
			is = value1.toString().equals(value2.toString());
		} else if(null == value1 && null == value1) {
			is = true;
		}
		return is;
	}
	
	/**
	 * 判断值是否不相等
	 * @param value1
	 * @param value2
	 * @return 相等返回：true；否则返回：false
	 */
	public static boolean isNotEquals(Object value1, Object value2) {
		return !isEquals(value1, value2);
	}
	
	/**
	 * 判断值是否相等（不区分大小写）
	 * @param value1
	 * @param value2
	 * @return 相等返回：true；否则返回：false
	 */
	public static boolean isEqualsIgnoreCase(Object value1, Object value2) {
		boolean is = false;
		if(null != value1 && null != value1) {
			is = value1.toString().equalsIgnoreCase(value2.toString());
		} else if(null == value1 && null == value1) {
			is = true;
		}
		return is;
	}
	
	/**
	 * 判断值是否不相等（不区分大小写）
	 * @param value1
	 * @param value2
	 * @return 相等返回：true；否则返回：false
	 */
	public static boolean isNotEqualsIgnoreCase(Object value1, Object value2) {
		return !isEqualsIgnoreCase(value1, value2);
	}
	
	/**
	 * null转换为“” 
	 * <p>注：该方法已过时，请用{@link #handleNull(Object)} 代替</p>
     * @param obj
     * @return 返回处理后的结果
	 */
	@Deprecated
	public static String handNull(Object obj) {
	    return handleNull(obj);
	}
	
	/**
	 * null转换为“”
	 * @param obj
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
	 * @param obj
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
     * <p>注：该方法已过时，请用{@link #handleObj2Integer(Object)} 代替</p>
     * @param obj
     * @return 返回转化结果
     */
	public static Integer handObj2Integer(Object obj) {
	    return handleObj2Integer(obj);
	}
	
	
	/**
	 * 对象转化为整型
	 * @param obj
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
     * <p>注：该方法已过时，请用{@link #handleNumNull(Object)} 代替</p>
     * @param obj
     * @return 返回转化结果
     */
	@Deprecated
	public static String handNumNull(Object obj) {
	    return handleNumNull(obj);
	}
	
	/**
	 * 数字null转换为“0”
	 * @param obj
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
	 * @param value
	 * @return 数字返回：true；否则返回：false
	 */
	public static boolean isNum(String value) {
		boolean is = false;
		Pattern pattern = Pattern.compile("\\d+|\\d+\\.\\d+");
		Matcher matcher = pattern.matcher(value);
		if(matcher.matches()) {
			is = true;
		} else {
			is = false;
		}
		return is;
	}
	
	
	/**
	 * 判断是否数字整数
	 * @param value
	 * @return 是返回：true；否则返回：false
	 */
	public static boolean isInteger(String value) {
		boolean is = false;
		Pattern pattern = Pattern.compile("\\d+");
		if(null != value && value.length()>1) {
			pattern = Pattern.compile("^[1-9]\\d+");
		}
		Matcher matcher = pattern.matcher(value);
		if(matcher.matches()) {
			is = true;
		} else {
			is = false;
		}
		return is;
	} 
	
	/**
	 * 判断是否小数
	 * @param value
	 * @return 是返回：true；否则返回：false
	 */
	public static boolean isDecimal(String value) {
		boolean is = false;
		Pattern pattern = Pattern.compile("\\d+\\.\\d+");
		Matcher matcher = pattern.matcher(value);
		if(matcher.matches()) {
			is = true;
		} else {
			is = false;
		}
		return is;
	} 
	
	
	/**
	 * 随机生成数
	 * @param num 要生成随机数的个数
	 * @return 返回随机生成数
	 */
	public static String randomNum(int num) {
		Random random = new Random();
		String numStr = "";
		for (int i = 0; i < num; i++) {
			numStr += random.nextInt(10);
		}
		return numStr;
	}
	
	
	/**
	 * 按日期格式生成序列号
	 * @param dateFormaterStr 日期格式
	 * @return 返回序列号
	 */
	public static String createSerialNum(String dateFormaterStr) {
		String serialNum = null;
		if(!isEmpty(dateFormaterStr)) {
			SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormaterStr);
			serialNum = dateFormatter.format(new Date());
			dateFormatter = null;
		}
		return serialNum;
	}
	
	
	/**
	 * 生成序列号；
	 * TODO 改为采用UUID生成序列号
	 * 生成的序列号；如：U+时间＋５位随机序列号
	 * 如:U141264925197606685
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
	 * @param fileName
	 * @return 返回文件后缀
	 */
	public static String getFileSuffix(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".")+1);
	}
	
	
	/**
	 * 去掉文件后缀
	 * @param fileName
	 * @return 返回文件后缀
	 */
	public static String trimFileSuffix(String fileName) {
		return fileName.substring(0,fileName.lastIndexOf("."));
	}
	
	
	/**
	 * 大写字母直接用下划线分割，并把大写转换为小写 <br />
	 * 如:HelloWorld转换为hello_world
	 * @param value
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
					    strBuilder.append(String.valueOf((char)((int)values[i]+32)));
					}
				} else {
				    strBuilder.append(String.valueOf((char)values[i]));
				}
			}
			values = null;
		}
		return strBuilder != null ? strBuilder.toString() : null;
	}
	
	/**
	 * 首字母转为大写
	 * @param value
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
	 * @param htmlContent
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
	 * @param phoneNo
	 * @return 验证成功返回：true；否则返回：false
	 */
	public static boolean isPhoneNO(String phoneNo){
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(phoneNo);
		return m.matches();
	}
	
	
	/**
	 * 验证固定电话号码
	 * @param tel
	 * @return 验证成功返回：true；否则返回：false
	 */
	public static boolean isFixedTelephone(String tel) {
		Pattern p = Pattern.compile("^[0][0-9]{2,3}-[2-9][0-9]{6,7}(-[0-9]{1,4})?");
		Matcher m = p.matcher(tel);
		return m.matches();
	}
	
	
	/**
	 * 验证匿名
	 * @param anonymous
	 * @return 验证成功返回：true；否则返回：false
	 */
	public static boolean isAnonymous(String anonymous){
		Pattern p = Pattern.compile("^[\\u4e00-\\u9fa5|A-Za-z]([\\w|\\u4e00-\\u9fa5]){1,7}$");
		Matcher m = p.matcher(anonymous);
		return m.matches();
	}
	
	/**
	 * 验证汉字
	 * @param value
	 * @return 验证成功返回：true；否则返回：false
	 */
	public static boolean isChinese(String value){
		Pattern p = Pattern.compile("^[\\u4E00-\\u9FFF]+$");
		Matcher m = p.matcher(value);
		boolean is = m.matches();
		return is;
	}
	
	/**
	 * 验证正则表达式
	 * @param value
	 * @return 验证成功返回：true；否则返回：false
	 */
	public static boolean isCheckRegex(String value,String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);
		boolean is = m.matches();
		return is;
	}
	
	/**
	 * 验证QQ号码
	 * @param value
	 * @return 验证成功返回：true；否则返回：false
	 */
	public static boolean isQQ(String value) {
		Pattern p = Pattern.compile("^[1-9]\\d{6,11}$");
		Matcher m = p.matcher(value);
		return m.matches();
	}
	
	
	/**
	 * 验证email
	 * @param email
	 * @return 验证成功返回：true；否则返回：false
	 */
	public static boolean isEmail(String email) {
		Pattern p = Pattern.compile("^([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$");
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
	/**
	 * 秒转化为:HH:mm:SS格式
	 * @param second
	 * @return 返回转化后的结果
	 */
	public static String secondToHHMMSS(long second){
		long h=0,m=0,s=0,tmp=0;
		if(second>=3600){
		   h= second/3600;
		   tmp = second%36000;
		   if(tmp>=60) {
			   m = tmp/60;
			   s = tmp%60;
		   }else {
		     s = tmp;
		   }
		}else if(second>=60){
		   m = second/60;
		   s = second%60;
		}else {
		   s = second;
		}
		return (h>9?h:"0"+h)+":"+(m>9?m:"0"+m)+":"+(s>9?s:"0"+s);
	 }
	
	
	/**
	 * 过滤特殊字符
	 * @param params
	 * @return 返回过滤后的结果
	 */
	public static String filterSQLParams(String params) {
		if(!isEmpty(params)) {
		    StringBuilder strBuilder = new StringBuilder();
		    strBuilder.append("'|\"|update|delete|select|drop|insert|=|;|0x|\\(|\\)|\\s|\\*|\\?|\\%|\\$");
		    strBuilder.append("|and|exec|execute|chr|mid|master|truncate|char|declare|sitename|net user|xp_cmdshell|or");
		    strBuilder.append("|\\+|,|like'|table|from|grant|use|group_concat|column_name|information_schema.columns");
		    strBuilder.append("|table_schema|union|where|order|by|count");
		    strBuilder.append("|--|,|like|//|/|#");
		    String params1 = params.toLowerCase();

		    params1 = params1.replaceAll(strBuilder.toString(), "");
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
	 * 根据regex分隔字符串
	 * 然后用逗号","重组
	 * @param ids
	 * @param regex
	 * @return 返回处理后的结果
	 */
	public static String splitIds(String ids,String regex){
		String newIds = "";
		if(null != ids && !StringUtils.isEmpty(ids.toString())) {
			String[] idsArr = ids.split(regex);
			for (int i = 0; i < idsArr.length; i++) {
				if(i != (idsArr.length-1)) {
					newIds += "'"+idsArr[i]+"',";
				} else {
					newIds += "'"+idsArr[i]+"'";
				}
			}
		}
		return newIds;
	}
	
	
	/**
	 * 计算出文件大小
	 * @param size
	 * @return 返回处理后的结果;<br />
	 * 格式为："100 KB"或”100 M“或”100 G“
	 */
	public static String fileSize(long size) {
		DecimalFormat df = new DecimalFormat("0.0#");
		long KB = 1024;
		long MB = KB*1024;
		long GB = MB*1024;
		String valueStr = null;
		if(size<0) {
			valueStr = "0 KB";
		} else if(size<KB*1024) {
			double value = (double)size/KB;
			valueStr = df.format(value)+" KB";
		} else if(size<MB*1024) {
			double value = (double)size/MB;
			valueStr = df.format(value)+" M";
		} else {
			double value = (double)size/GB;
			valueStr = df.format(value)+" G";
		}
		df = null;
		return valueStr;
	}
	
	
	/**
	 * 过滤目录结构(防止参数传递目录结构)
	 * @param value
	 * @return 返回过滤后的结果
	 */
	public static String filterFilePath(String value) {
		if(!StringUtils.isEmpty(value)) {
			value.replaceAll("\\.|/|\\\\\\\\|\\\\|:|%2F|%2E|25%|20%|%5C|60%|27%|%3A|%2A","");
		}
	    return value;
	}
	
	/**
	 * 判断是否含有contain指定的值
	 * @param value
	 * @param contain
	 * @return 包含返回：true；否则返回：false
	 */
	public static boolean isContains(String value,String contain) {
		boolean is = false;
		if(!isEmpty(value) && null != contain) {
			is = value.indexOf(contain)>-1?true:false;
		}
		return is;
	}
	
	
	/**
	 * list转换为数组
	 * @param values
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
	 * @param ip
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
	 * 集合转换为字符串，字符串之间用separater提供的参数分隔
	 * @param values
	 * @param separater
	 * @return 返回处理后的结果
	 */
	public static <T> String collection2String(Collection<T> values,String separater) {
	    StringBuilder strBuilder = null;
		if(null != values && values.size()>0) {
		    strBuilder = new StringBuilder();
			if(StringUtils.isEmpty(separater)) {
				separater = "";
			}
			for (T value : values) {
			    strBuilder.append(StringUtils.handleNull(value) + separater);
			}
			if(!StringUtils.isEmpty(separater)) {
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
	public static String repaceSpecialChar(String value) {
		if(isNotEmpty(value)) {
			value = value.replaceAll("\r|\n|\t", "");
		}
		return value;
	}
	
	/**
	 * 替换斜杠
	 * @param value
	 * @return 返回处理后的结果
	 */
	public static String repaceSlash(String value) {
		if(isNotEmpty(value)) {
			//value = value.replaceAll("\\\\", "\\\\\\\\\\\\\\\\");
			value = value.replaceAll("\\\\", "\\\\\\\\");
		}
		return value;
	}
	
	/**
	 * 字符串转换为list
	 * @param value 要转换的字符串
	 * @param separater 分隔符
	 * @return 返回转换后的List
	 */
	public static List<String> string2List(String value, String separater) {
		List<String> lists = null;
		if(isNotEmpty(value)) {
			separater = isEmpty(separater)?",":separater;
			String[] array = value.split(separater);
			lists = Arrays.asList(array);
		}
		return lists;
	}
	
	/**
     * 字符串转换为Set
     * @param value 要转换的字符串
     * @param separater 分隔符
     * @return 返回转换后的Set
     */
    public static Set<String> string2Set(String value, String separater) {
        Set<String> sets = null;
        if(isNotEmpty(value)) {
            separater = isEmpty(separater) ? "," : separater;
            String[] array = value.split(separater);
            sets = new HashSet<String>();
            sets.addAll(Arrays.asList(array));
        }
        return sets;
    }
	
	/**
	 * 处理URL参数；
	 * 判断 <code>url</code> 中是否有“?”；
	 * 如果有则会在 <code>url</code> 后面加“&”，没有则加“?”
	 * @param url URL字符串
	 * @return 返回处理后的URL
	 */
	public static String handleUrlParam(String url) {
	    if(isEmpty(url)) {
	        return url;
	    }
	    StringBuilder urlBuilder = new StringBuilder(url);
	    if(url.indexOf("?") == -1) {
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
	 * @param value
	 * @return
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
	 * 如果<code>separate</code>为空，则采用默认值；默认值为：{@link MarkbenConstant#MULTI_VALUE_SPLIT}
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
		for (int i=0; i < objs.length; i++) {
			if(i < objs.length-1 ) {
				strBuilder.append(String.valueOf(objs[i]) + separate);
			} else {
				strBuilder.append(String.valueOf(objs[i]));
			}
		}//for
		objs = null;
		return (null != strBuilder)?strBuilder.toString():null;
	}


	/**
	 * 字符串转化为数组；
	 * 如果<code>separate</code>为空，则采用默认值；默认值为：{@link MarkbenConstant#MULTI_VALUE_SPLIT}
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
		value = null;
		return array;
	}


	/**
	 * 按separate分离成数组,判断该数组里面是否包含subStr；
	 * 如果<code>separate</code>为空，则采用默认值；默认值为：{@link MarkbenConstant#MULTI_VALUE_SPLIT}
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
	 * 填补位数；如果<code>fillText</code>为空，则默认填充内容为“0”
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
}
