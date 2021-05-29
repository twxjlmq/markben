package com.markben.rest.common;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.markben.common.logger.Logger;
import com.markben.common.utils.LoggerUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 驼峰命名请求参数处理类
 */
public class SnakeToCamelArgumentResolver implements HandlerMethodArgumentResolver {

    private static MappingJackson2HttpMessageConverter converter;
    
    private static ObjectMapper objectMapper;
    
    private static final Logger logger = LoggerUtils.getLogger(SnakeToCamelArgumentResolver.class);
    
    static {
        converter = new MappingJackson2HttpMessageConverter();
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        LoggerUtils.debug(logger, "执行的方法为:{}", parameter.getMethod().getName());
        return parameter.hasParameterAnnotation(SnakeCamelRequest.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest servletRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        ServletServerHttpRequest inputMessage = new ServletServerHttpRequest(servletRequest);
        Object result =null;
        try {
            Type genericParameterType = methodParameter.getGenericParameterType();
            String contentType = servletRequest.getContentType();
            if(MediaType.APPLICATION_JSON_VALUE.equals(contentType)){
                result = converter.read(Class.forName(genericParameterType.getTypeName()), inputMessage);
            } else {
                Map<String, String[]> parameterMap = nativeWebRequest.getParameterMap();
                Map<String, Object> args = new HashMap<String, Object>(parameterMap.size());
                parameterMap.forEach((key, value) -> {
                    if(value.length > 1) {
                        args.put(key, value);
                    } else {
                        args.put(key, value[0]);
                    }
                });
                String paramStr = objectMapper.writeValueAsString(args);
                result = objectMapper.readValue(paramStr, methodParameter.getParameterType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
