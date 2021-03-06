package com.markben.restful.common.helper;

import com.markben.beans.bean.UserInfo;
import com.markben.common.constant.MarkbenConstant;
import com.markben.common.utils.CollectionUtils;
import com.markben.common.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Http请求辅助类
 * @author 乌草坡
 * @since 0.0.1
 */
public class HttpRequestHelper {

    /**
     * 获取当前URI(不含参数)
     * @param request 请求对象
     * @return 返回当前访问的地址
     */
    public static String getCurrentUri(HttpServletRequest request) {
        String currentUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        if(StringUtils.isNotEmpty(contextPath)) {
            currentUri = currentUri.replaceFirst(request.getContextPath()+"/", "");
        } else {
            currentUri = currentUri.substring(1);
        }
        return currentUri;
    }

    /**
     * 获取当前URI
     * @param request 请求对象
     * @return 返回当前访问的地址（含参数）
     */
    public static String getCurrentUriParam(HttpServletRequest request) {
        String currentUri = getCurrentUri(request);
        if(StringUtils.isNotEmpty(request.getQueryString())) {
            currentUri +="?"+request.getQueryString();
        }
        try {
            currentUri = URLDecoder.decode(currentUri, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return currentUri;
    }

    /**
     * 设置session值
     * @param request 请求对象
     * @param key KEY
     * @param value 值对象
     */
    public static void setSession(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession();
        if(null != session) {
            session.setAttribute(key, value);
        }
    }

    /**
     * 获取session值
     * @param request 请求对象
     * @param key KEY
     * @return 返回KEY对应的值
     */
    public static Object getSession(HttpServletRequest request, String key) {
        HttpSession session = request.getSession();
        Object obj = null;
        if(null != session) {
            obj = session.getAttribute(key);
        }
        return obj;
    }

    /**
     * 添加用户信息到session
     * @param session HttpSession
     * @param userInfo 用户信息对象
     */
    public static void setUserInfoToSession(HttpSession session, UserInfo userInfo) {
        if(null != session) {
            String token = userInfo.getToken();
            if(StringUtils.isEmpty(token)) {
                token = generateToken(session);
            }
            setUserInfoToSession(session, token, userInfo);
        }
    }

    /**
     * 添加用户信息到session
     * @param session HttpSession
     * @param token Token
     * @param userInfo 用户信息对象
     */
    public static void setUserInfoToSession(HttpSession session, String token, UserInfo userInfo) {
        if(null == session) {
            throw new IllegalArgumentException("session参数为空");
        }
        if(StringUtils.isEmpty(token)) {
            token = userInfo.getToken();
        }
        if(StringUtils.isEmpty(token)) {
            token = generateToken(session);
        }
        userInfo.setToken(token);
        session.setAttribute(token, userInfo);
    }

    /**
     * 生成token
     * @param session HttpSession
     * @return 返回token
     */
    public static String generateToken(HttpSession session) {
        return session.getId();
    }

    /**
     * 从session中获取用户信息
     * @param session HttpSession
     * @param token Token
     * @return 返回用户信息对象
     */
    public static UserInfo getUserInfoFromSession(HttpSession session, String token) {
        UserInfo userInfo = null;
        if(null != session) {
            userInfo = (UserInfo)session.getAttribute(token);
        }
        return userInfo;
    }


    /**
     * 添加用户信息到session
     * @param request Http请求对象
     * @param userInfo 用户信息对象
     */
    public static void setUserInfoToSession(HttpServletRequest request, UserInfo userInfo) {
        if(null != request) {
            String token = userInfo.getToken();
            if(StringUtils.isEmpty(token)) {
                token = generateToken(request.getSession());
            }
            setUserInfoToSession(request.getSession(), token, userInfo);
        }
    }

    /**
     * 添加用户信息到session
     * @param request Http请求对象
     * @param userInfo 用户信息对象
     */
    public static void setUserInfoToSession(HttpServletRequest request, String token, UserInfo userInfo) {
        if(null != request) {
            setUserInfoToSession(request.getSession(), token, userInfo);
        }
    }


    /**
     * 从session中获取用户信息
     * @param request Http请求对象
     * @return 返回用户信息对象
     */
    public static UserInfo getUserInfoFromSession(HttpServletRequest request) {
        return getUserInfoFromSession(request, null);
    }

    /**
     * 从session中获取用户信息
     * @param request Http请求对象
     * @return 返回用户信息对象
     */
    public static UserInfo getUserInfoFromSession(HttpServletRequest request, String token) {
        UserInfo userInfo = null;
        if(null != request) {
            if(StringUtils.isEmpty(token)) {
                token = getToken(request);
            }
            if(StringUtils.isNotEmpty(token)) {
                userInfo = getUserInfoFromSession(request.getSession(), token);
            } else {
                userInfo = getUserInfoFromSession(request);
            }
        }
        return userInfo;
    }

    /**
     * 从HTTP请求头上获取token值
     * @param request Http请求对象
     * @return 返回token
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader(MarkbenConstant.AUTHORIZATION_TOKEN_HEADER);
        if(StringUtils.isEmpty(token)) {
            token = request.getSession().getId();
        }
        return token;
    }

    /**
     * 获取客户端IP地址
     * @param request Http请求对象
     * @return 返回客户端IP地址
     */
    public static String getIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        ip = "unknown".equalsIgnoreCase(ip) ? "" : ip;
        if(StringUtils.isNotEmpty(ip) && ip.indexOf(MarkbenConstant.MULTI_VALUE_SPLIT)>-1) {
            String[] ipArray = ip.split(MarkbenConstant.MULTI_VALUE_SPLIT);
            for (String ipTmp : ipArray) {
                if(!"unknown".equals(ipTmp)) {
                    ip = ipTmp;
                    break;
                }
            }
        }
        return ip;
    }

    /**
     * 获取域名（含端口）
     * @param request Http请求对象
     * @return 返回域名
     */
    public static String getDomain(HttpServletRequest request) {
        String domain = null;
        String path = request.getContextPath();
        domain = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        return domain;
    }


    /**
     * 获取当前URL(不含参数)
     *
     * @param request Http请求对象
     * @return 获取当前访问地址
     */
    public static String getCurrentUrl(HttpServletRequest request) {
        return request.getRequestURL().toString();
    }

    /**
     * 获取当前URL（含参数）
     * @param request Http请求对象
     * @return 返回当前访问地址（含参数）
     */
    public static String getCurrentUrlParam(HttpServletRequest request) {
        String urlStr = getCurrentUrl(request);
        String queryStr = request.getQueryString();
        if (StringUtils.isNotEmpty(queryStr)) {
            try {
                queryStr = URLDecoder.decode(queryStr, "UTF-8");
                urlStr = urlStr + "?" + queryStr;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return urlStr;
    }


    /**
     * 添加系统变量
     * @param request Http请求对象
     * @param param Map参数对象
     * @return 返回处理后的Map对象
     */
    public static Map<String, Object> addSystemVariable(HttpServletRequest request, Map<String, Object> param) {
        if(null == param) {
            param = new HashMap<>();
        }
        UserInfo userInfo = getUserInfoFromSession(request);
        param.put("userId", userInfo.getUserId());
        param.put("username", userInfo.getUsername());
        param.put("nickname", userInfo.getNickname());
        param.put("mobile", userInfo.getMobile());

        return param;
    }
}
