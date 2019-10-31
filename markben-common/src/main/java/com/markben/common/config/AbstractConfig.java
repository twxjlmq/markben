package com.markben.common.config;

import com.markben.common.logger.ILogger;
import com.markben.common.utils.LoggerUtils;
import com.markben.common.utils.StringUtils;
import org.slf4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 配置抽象类
 * @author lmq
 * @version 1.0
 * @since JDK版本大于等于1.6
 * 2016年1月21日
 */
public abstract class AbstractConfig {

	protected final ILogger logger;

	public AbstractConfig() {
	    logger = LoggerUtils.getLogger(getClass());
    }

    /**
	 * 变量处理
	 * @param value 变量名称
	 * @return 返回变量值
	 */
	protected String handleVar(String value) {
		if(StringUtils.isNotEmpty(value)) {
			String regex = "(?<=\\$\\{)[^\\{\\}]+(?=\\})";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(value);
			while(matcher.find()) {
				String varName = matcher.group();
				if(StringUtils.isNotEmpty(varName)) {
					String val = null;
					try {
						val = getValue(varName);
					} catch (Exception e) {
						LoggerUtils.info(logger, "变量【"+val+"】没定义");
						e.printStackTrace();
						val = null;
					}
					if(StringUtils.isEmpty(val)) {
					    LoggerUtils.info(logger, "变量【"+val+"】没定义");
					}
					value = value.replace("${"+varName+"}", StringUtils.handleNull(val));
				}
			}
			matcher = pattern.matcher(value);
			if(matcher.find()) {
				handleVar(value);
			}
			pattern = null;
			matcher = null;
			return value;
		}
		return value;
	}

	/**
	 * 处理值中含有的系统变量
	 * @param value 变量名称
	 * @return 返回系统变量值
	 */
	protected String handleSysVar(String value) {
		String regex = "(?<=#\\{)[^\\{\\}]+(?=\\})";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		while(matcher.find()) {
			String varName = matcher.group();
			if(StringUtils.isNotEmpty(varName)) {
				String val = null;
				try {
				   val = System.getProperty(varName);
				} catch (Exception e) {
				    LoggerUtils.info(logger, "系统属性【"+val+"】没有找到");
					e.printStackTrace();
					val = null;
				}
				value = value.replace("#{"+varName+"}", StringUtils.handleNull(val));
			}
		}
		matcher = pattern.matcher(value);
		if(matcher.find()) {
			handleSysVar(value);
		}
		pattern = null;
		matcher = null;
		return value;
	}
	
	/**
	 * 获取变量值
	 * @param key 名称
	 * @return 获取名称对应的值
	 */
	public abstract String getValue(String key);
	
	/**
	 * 获取变量值
	 * @param key 名称
	 * @param defaultValue 如果获取到的值为空（null），则使用该值
	 * @return 获取名称对应的值，如果获取到的值为空（null），则返回默认值
	 */
	public abstract String getValue(String key, String defaultValue);
	
}
