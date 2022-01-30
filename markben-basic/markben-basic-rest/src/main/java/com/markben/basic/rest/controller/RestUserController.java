package com.markben.basic.rest.controller;

import com.markben.basic.common.service.UserService;
import com.markben.basic.rest.vo.ChangePwdRequest;
import com.markben.beans.bean.UserInfo;
import com.markben.beans.enums.MarkbenStatusEnums;
import com.markben.common.utils.LoggerUtils;
import com.markben.restful.common.controller.AbstractRestController;
import com.markben.restful.common.response.RestBaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 乌草坡
 * @since 0.0.1
 */
@RestController
@RequestMapping("/rest/user")
@Api(value = "用户相关接口", tags = "用户相关接口")
public class RestUserController extends AbstractRestController {

    private UserService userService;

    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 修改密码
     * @param request Http请求对象
     * @param pwdRequest 修改密码请求对象
     * @return 返回修改密码的结果
     */
    @PostMapping(value = "/change/password", produces = PRODUCES_FORMAT)
    @ApiOperation(value = "修改密码接口", notes = "修改密码接口")
    public RestBaseResponse changePassword(HttpServletRequest request, @RequestBody ChangePwdRequest pwdRequest) {
        super.checkRequestVO(pwdRequest);
        RestBaseResponse restResp = new RestBaseResponse();
        if(!pwdRequest.getConfirmNewPassword().equals(pwdRequest.getNewPassword())) {
            LoggerUtils.error(getLogger(), "两次输入的信息密码不一致");
            setResponseStatus(restResp, MarkbenStatusEnums.CHANGE_PASSWORD_NEW_VALUE_ERROR);
            return restResp;
        }
        UserInfo userInfo = super.getUserInfoByRequest(request);
        String userId = userInfo.getUserId();
        userService.changePassword(userId, pwdRequest.getNewPassword(), pwdRequest.getOldPassword());
        setSuccessResult(restResp);
        return restResp;
    }

}
