package com.markben.common.config;

import com.markben.common.ProjectInfo;
import com.markben.common.constant.MarkbenConstant;
import com.markben.common.utils.LoggerUtils;
import com.markben.common.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * 初始化系统配置文件
 * @author lmq
 * @version 1.0
 * @since JDK版本大于等于1.6
 * 2016年1月21日
 */
public class SystemConfig extends AbstractConfig {

	/**
	 * 系统配置文件
	 */
	public static final String SYS_CONFIG_FILE = "/sysconfig.properties";
	
	private static SystemConfig instance;
	
	private String sysConfigPath;
	
	private long lastModifyTime = 0;
	
	private Properties prop = null;
	
	private SystemConfig(String sysConfigPath) {
		if(StringUtils.isNotEmpty(sysConfigPath)) {
			this.sysConfigPath = sysConfigPath;
		} else {
			this.sysConfigPath = SYS_CONFIG_FILE;
		}
		init();
	}
	
	private SystemConfig() {
		this.sysConfigPath = SYS_CONFIG_FILE;
		init();
	}
	
	/**
	 * 重新加载配置文件
	 */
	private void reloadConfig() {
		String devModel = instance.getValue("project.devModel");
		if(StringUtils.isNotEmpty(devModel) && MarkbenConstant.PROJECT_DEV_MODEL.equals(devModel)) {
			URL path = this.getClass().getResource(this.sysConfigPath);
			File file = new File(path.getFile());
			path = null;
			if(file.exists()) {
				//判断文件是否被修改过，如果文件已修改，则重新初始化文件
				if(file.lastModified()>lastModifyTime) {
					reInit();
				}
			}
			file = null;
		}
	}
	
	/**
	 * 获取实例
	 * @return SystemConfig
	 */
	public synchronized static SystemConfig getInstance() {
		if(null == instance) {
			instance = new SystemConfig();
		}
		instance.reloadConfig();
		return instance;
	}
	
	/**
	 * 初始化配置文件
	 */
	protected void init() {
	    LoggerUtils.info(logger, "初始化配置文件-------");
		InputStream in = null;
		try {
		  URL url = this.getClass().getResource(this.sysConfigPath);
		  in = url.openStream();
		  if(null != in) {
			  prop = new Properties();
			  prop.load(in);
		  }
		  File file = new File(url.getFile());
		  lastModifyTime  = file.lastModified();
		  file = null;
		  LoggerUtils.info(logger, "初始化配置文件[结束]-------");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public String getValue(String key) {
		String value = null;
		if(null != prop && StringUtils.isNotEmpty(key)){
			try {
				value = StringUtils.handleNull(prop.get(key));
				value = handleSysVar(value);
				value = handleVar(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	
	/**
	 * 获取项目信息
	 * @return 获取项目信息对象
	 */
	public ProjectInfo getProjectInfo() {
		ProjectInfo projectInfo = null;
		if(null != prop){
			projectInfo = new ProjectInfo();
			projectInfo.initParam("project", this);
		}
		return projectInfo;
	}
	
	/**
	 * 重新初始化配置文件
	 */
	public void reInit() {
		init();
	}

    @Override
    public String getValue(String key, String defaultValue) {
        String value = getValue(key);
        if(StringUtils.isEmpty(value)) {
            value = defaultValue;
        }
        return value;
    }
	
	
}
