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
     * @param request Http请求对象
     * @return 返回用户信息对象
     */
    protected IUserInfo getUserInfoByRequest(HttpServletRequest request) {
        return HttpRequestHelper.getUserInfoFromSession(request);
    }


}
