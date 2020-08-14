package com.markben.rest.common.exception;

import com.markben.beans.enums.MarkbenStatusEnums;
import com.markben.common.exception.NullArgumentException;
import com.markben.common.logger.ILogger;
import com.markben.common.utils.LoggerUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 统一异常处理
 * @author 乌草坡
 * @since 1.0
 */
public class GlobalApiExceptionHandler extends AbstractHandlerExceptionResolver {

    private static final ILogger logger = LoggerUtils.getLogger(GlobalApiExceptionHandler.class);
    
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, 
            Object handler, Exception ex) {
        ModelAndView modelView = new ModelAndView();
        String msg = ex.getMessage();
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        Map<String, Object> modelMap = modelView.getModelMap();
        Integer code = 0;
        if(ex instanceof NullArgumentException
                || ex instanceof IllegalArgumentException) {
            code = MarkbenStatusEnums.REQUEST_ARG_ERROR.getStatus();
            LoggerUtils.error(logger, ex.getMessage());
        } else if(ex instanceof RestAuthorizationException) {
            code = MarkbenStatusEnums.UNAUTHORIZED.getStatus();
            LoggerUtils.error(logger, ex.getMessage());
        } else {
            code = MarkbenStatusEnums.FAIL.getStatus();
            LoggerUtils.error(logger, ex.getMessage(), ex);
        }
        modelMap.put("status", code);
        modelMap.put("msg", msg);
        request.setAttribute("status", code);
        request.setAttribute("msg", msg);
        modelView.setView(jsonView);
        LoggerUtils.error(logger, ex);
        return modelView;
    }

}
