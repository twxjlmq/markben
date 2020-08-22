package com.markben.rest.common.controller;

import com.markben.beans.bean.IUserInfo;
import com.markben.common.logger.ILogger;
import com.markben.common.utils.LoggerUtils;
import com.markben.rest.common.helper.HttpRequestHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * 抽象基础控制器类
 * @author 乌草坡
 * @since 1.0
 */
public abstract class AbstractBaseController {

    private ILogger logger;

    public AbstractBaseController() {
        logger = LoggerUtils.getLogger(getClass());
    }

    public ILogger getLogger() {
        return logger;
    }

    /**
     * 获取用户信息从本次请求对象中
     * @param request HttpServletRequest对象
     * @return 返回用户信息对象
     */
    protected IUserInfo getUserInfoByRequest(HttpServletRequest request) {
        return HttpRequestHelper.getUserInfoFromSession(request);
    }

    /**
     * 添加用户信息到session
     * @param request HttpServletRequest对象
     * @param userInfo 用户信息
     */
    protected void setUserInfo2Session(HttpServletRequest request, IUserInfo userInfo) {
        if(null != request && null != userInfo) {
            HttpRequestHelper.setUserInfoToSession(request, userInfo);
        }
    }

    /**
     * 从session中获取用户信息
     * @param request HttpServletRequest对象
     * @return 返回session中的用户信息
     */
    protected IUserInfo getUserInfoFromSession(HttpServletRequest request) {
        IUserInfo userInfo = null;
        if(null != request) {
            userInfo = HttpRequestHelper.getUserInfoFromSession(request);
        }
        return userInfo;
    }
}
