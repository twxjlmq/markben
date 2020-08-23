package com.markben.rest.common.interceptor;

import com.markben.beans.bean.IUserInfo;
import com.markben.common.logger.ILogger;
import com.markben.common.utils.LoggerUtils;
import com.markben.common.utils.StringUtils;
import com.markben.rest.common.exception.NotLoginException;
import com.markben.rest.common.helper.HttpRequestHelper;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 默认拦截器
 * @author 乌草坡
 * @since 1.0
 */
public class DefaultRestInterceptor implements HandlerInterceptor {

    private static final ILogger logger = LoggerUtils.getLogger(DefaultRestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String currentUri = HttpRequestHelper.getCurrentUri(request);
        HttpSession session = request.getSession();
        String method = request.getMethod();
        LoggerUtils.debug(logger, "访问地址为：[{}]---sessionId为: [{}]---method: [{}]---", currentUri, session.getId(), method);
        Date currentTime = new Date();
        long startTime = currentTime.getTime();
        request.setAttribute("startTime", startTime);
        if(RequestMethod.OPTIONS == RequestMethod.valueOf(method)) {
            return true;
        } else {
            if (isLogin(request)) {
                return true;
            } else {
                throw new NotLoginException();
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String currentUrl = HttpRequestHelper.getCurrentUriParam(request);
        long startTime = (Long) request.getAttribute("startTime");
        Date responseTime = new Date();
        long endTime = responseTime.getTime();
        long useTime = endTime - startTime;
        LoggerUtils.debug(logger, "请求[{}]---用时：{}毫秒", currentUrl, useTime);
    }

    /**
     * 判断用户是否登录
     *
     * @param request
     * @return
     */
    private boolean isLogin(HttpServletRequest request) {
        boolean is = false;
        if (null != request) {
            IUserInfo userInfo = HttpRequestHelper.getUserInfoFromSession(request);
            if(null != userInfo && StringUtils.isNotEmpty(userInfo.getCorpId())) {
                LoggerUtils.debug(logger, "当前企业用户ID为：[{}]---部门ID为: deptId:[{}].",
                        userInfo.getCorpUserId(), userInfo.getDeptId());
                is = true;
            }
        }
        return is;
    }
}
