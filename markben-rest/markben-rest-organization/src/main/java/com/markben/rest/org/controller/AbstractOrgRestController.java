package com.markben.rest.org.controller;

import com.markben.beans.bean.UserInfo;
import com.markben.rest.common.controller.AbstractRestController;
import com.markben.rest.org.bean.OrgUserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 抽象REST接口控制器类
 * @author 乌草坡
 * @since 0.0.1
 */
public abstract class AbstractOrgRestController extends AbstractRestController {

    @Override
    protected OrgUserInfo getUserInfoByRequest(HttpServletRequest request) {
        return (OrgUserInfo) super.getUserInfoByRequest(request);
    }

    @Override
    protected void setUserInfo2Session(HttpServletRequest request, UserInfo userInfo) {
        super.setUserInfo2Session(request, userInfo);
    }

    @Override
    protected OrgUserInfo getUserInfoFromSession(HttpServletRequest request) {
        return (OrgUserInfo)super.getUserInfoFromSession(request);
    }
}
