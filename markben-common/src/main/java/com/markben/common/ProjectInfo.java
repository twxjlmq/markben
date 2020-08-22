package com.markben.common;

import com.markben.common.config.SystemConfig;
import com.markben.common.enums.ProjectEnvironmentType;
import com.markben.common.utils.StringUtils;

import java.io.Serializable;


/**
 * 项目信息
 * @author 乌草坡
 * @since 1.0
 */
public class ProjectInfo implements Serializable {

	/**
	 * 项目名称
	 */
	private String name;
	
	/**
	 * 项目环境；
	 * 如： development--开发环境；
	 * test--测试环境；
	 * production--生产环境
	 */
	private Integer environment;
	
	/**
	 * 版权
	 */
	private String copyright;
	
	/**
	 * 联系方式
	 */
	private String contactInfo;
	
	/**
	 * 版本
	 */
	private String version;

	/**
	 * 获取项目名称
	 * @return 项目名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置项目名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取项目环境；
	 * 具体的类型请参看{@link ProjectEnvironmentType }中的定义
	 * @return 返回环境类型值
	 */
	public Integer getEnvironment() {
		if(null == environment) {
			environment = ProjectEnvironmentType.DEVELOPMENT.getIndex();
		}
		return environment;
	}

	/**
	 * 设置项目环境；
	 * 具体的类型请参看{@link ProjectEnvironmentType }中的定义
	 * @param environment 项目环境类型值；
	 */
	public void setEnvironment(Integer environment) {
		this.environment = environment;
	}

	/**
	 * 版权
	 * @return 返回版权信息
	 */
	public String getCopyright() {
		return copyright;
	}

	/**
	 * 设置版权
	 * @param copyright
	 */
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	/**
	 * 获取联系信息
	 * @return 返回系统的联系信息
	 */
	public String getContactInfo() {
		return contactInfo;
	}

	/**
	 * 设置联系信息
	 * @param contactInfo
	 */
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	/**
	 * 版本信息
	 * @return 系统版本号
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * 设置版本信息
	 * @param version
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	/**
	 * 初始化参数值
	 * @param prefix 项目信息在配置文件中的前缀
	 * @param config 系统配置对象
	 */
	public void initParam(String prefix, SystemConfig config) {
		if(StringUtils.isNotEmpty(prefix)) {
			prefix += ".";
		}
		this.name = StringUtils.handleNull(config.getValue(prefix+"name"));
		
		String environmentStr = StringUtils.handleNull(config.getValue(prefix+"environment"));
		if(StringUtils.isEmpty(environmentStr) || !StringUtils.isInteger(environmentStr)) {
			this.environment = ProjectEnvironmentType.DEVELOPMENT.getIndex();
		} else {
			this.environment = ProjectEnvironmentType.getObject(Integer.parseInt(environmentStr)).getIndex();
		}
		this.copyright = StringUtils.handleNull(config.getValue(prefix+"copyright"));
			
		this.contactInfo = StringUtils.handleNull(config.getValue(prefix+"contactInfo"));
		this.version = StringUtils.handleNull(config.getValue(prefix+"version"));
	}

	/**
	 * 获取当前项目的环境类型
	 * @return 返回当前项目的环境类型对象
	 */
	public ProjectEnvironmentType getEnvironmentType() {
		return ProjectEnvironmentType.getObject(getEnvironment());
	}
}
