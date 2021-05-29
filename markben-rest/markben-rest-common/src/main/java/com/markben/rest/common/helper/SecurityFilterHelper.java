package com.markben.rest.common.helper;

import com.markben.common.SystemInfo;
import com.markben.common.config.SystemConfig;

/**
 * 安全过滤辅助类
 * @author 乌草坡
 * @since 0.0.1
 */
public class SecurityFilterHelper {

    /**
     * 过滤密码处理；结合上下文，
     * 当系统在开发环境或测试环境时，密码明文显示；
     * 当系统在正式环境时，密码用六个*号显示
     * @param password
     * @return
     */
    public static String filterPassword(String password) {
       SystemInfo projectInfo = SystemConfig.getInstance().getProjectInfo();
       if(null == projectInfo) {
           return password;
       }
       if(projectInfo.getEnvironmentType().isDevelopment() || projectInfo.getEnvironmentType().isTest()) {
           return password;
       } else {
           return "******";
       }
    }

}
