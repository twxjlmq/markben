package com.markben.rest.common.controller;

import com.markben.beans.bean.UserInfo;
import com.markben.common.logger.Logger;
import com.markben.common.utils.LoggerUtils;
import com.markben.rest.common.helper.HttpRequestHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * 抽象基础控制器类
 * @author 乌草坡
 * @since 0.0.1
 */
public abstract class AbstractBaseController {

    private Logger logger;

    public AbstractBaseController() {
        logger = LoggerUtils.getLogger(getClass());
    }

    public Logger getLogger() {
        return logger;
    }

    /**
     * 获取用户信息从本次请求对象中
     * @param request HttpServletRequest对象
     * @return 返回用户信息对象
     */
    protected UserInfo getUserInfoByRequest(HttpServletRequest request) {
        return HttpRequestHelper.getUserInfoFromSession(request);
    }

    /**
     * 添加用户信息到session
     * @param request HttpServletRequest对象
     * @param userInfo 用户信息
     */
    protected void setUserInfo2Session(HttpServletRequest request, UserInfo userInfo) {
        if(null != request && null != userInfo) {
            HttpRequestHelper.setUserInfoToSession(request, userInfo);
        }
    }

    /**
     * 从session中获取用户信息
     * @param request HttpServletRequest对象
     * @return 返回session中的用户信息
     */
    protected UserInfo getUserInfoFromSession(HttpServletRequest request) {
        UserInfo userInfo = null;
        if(null != request) {
            userInfo = HttpRequestHelper.getUserInfoFromSession(request);
        }
        return userInfo;
    }
}
