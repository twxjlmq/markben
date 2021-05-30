package com.markben.rest.common.exception;

import com.markben.beans.enums.MarkbenStatusEnums;
import com.markben.beans.response.BaseResponse;
import com.markben.common.exception.NullArgumentException;
import com.markben.common.logger.Logger;
import com.markben.common.utils.LoggerUtils;
import com.markben.rest.common.response.RestBaseResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局控制器异常处理者类
 * @author 乌草坡
 * @since 0.0.1
 */
@RestControllerAdvice
public class ControllerExceptionHandler {
    
    private static final Logger logger = LoggerUtils.getLogger(ControllerExceptionHandler.class);
    
    /**
     * 处理未登录异常信息
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     * @param e 异常对象
     * @return 返回异常处理结果
     */
    @ExceptionHandler(value = NotLoginException.class)
    public BaseResponse unLoginErrorHandler(HttpServletRequest request, HttpServletResponse response , NotLoginException e) {
        BaseResponse baseResp = new RestBaseResponse();
        MarkbenStatusEnums status = MarkbenStatusEnums.NOT_LOGIN;
        baseResp.setStatus(status.getStatus());   //未登录异常码
        baseResp.setMsg(status.getMsg());
        return baseResp;
    }
    
    /**
     * 参数异常处理者
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     * @param e 异常对象
     * @return 返回异常处理结果
     */
    @ExceptionHandler(value = {RestArgumentException.class, NullArgumentException.class, IllegalArgumentException.class})
    public BaseResponse argumentExceptionHandler(HttpServletRequest request, HttpServletResponse response, RuntimeException e) {
        BaseResponse baseResp = new RestBaseResponse();
        MarkbenStatusEnums status = MarkbenStatusEnums.REQUEST_ARG_ERROR;
        baseResp.setStatus(status.getStatus());   //参数异常码
        baseResp.setMsg(e.getMessage());
        LoggerUtils.error(logger, e);
        return baseResp;
    }

    /**
     * 异常处理者
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     * @param e 异常对象
     * @return 返回异常处理结果
     */
    @ExceptionHandler(value = {Exception.class})
    public BaseResponse exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        BaseResponse baseResp = new RestBaseResponse();
        MarkbenStatusEnums status = MarkbenStatusEnums.FAIL;
        baseResp.setStatus(status.getStatus());   //其他异常
        baseResp.setMsg(e.getMessage());
        LoggerUtils.error(logger, e);
        return baseResp;
    }

}
