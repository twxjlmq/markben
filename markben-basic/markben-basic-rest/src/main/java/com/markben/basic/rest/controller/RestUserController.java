package com.markben.basic.rest.controller;

import com.markben.basic.rest.service.ILoginService;
import com.markben.basic.rest.vo.user.ConfirmLoginRequest;
import com.markben.basic.rest.vo.user.ConfirmLoginResultVO;
import com.markben.basic.rest.vo.user.LoginRequest;
import com.markben.basic.rest.vo.user.LoginResultVO;
import com.markben.beans.bean.IUserInfo;
import com.markben.beans.response.IResultResponse;
import com.markben.common.utils.LoggerUtils;
import com.markben.rest.common.controller.AbstractRestController;
import com.markben.rest.common.helper.HttpRequestHelper;
import com.markben.rest.common.helper.SecurityFilterHelper;
import com.markben.rest.common.response.RestResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户REST接口控制器类
 * @author 乌草坡
 * @since 1.0
 */
@RestController
@RequestMapping("/rest/user")
@Api(value = "用户接口", tags = {"用户接口"})
public class RestUserController extends AbstractRestController {

    @Autowired
    private ILoginService loginService;

    /**
     * 登录接口方法
     * @param loginRequest 登录请求对象
     * @return 返回结果
     */
    @PostMapping(value = "/login", produces = PRODUCES_FORMAT_TYPE)
    @ApiOperation(value = "登录接口", notes = "登录接口；注：调用登录接口之后需要再次调用“确认登录接口”后，才完成整个登录的过程。")
    @ApiImplicitParam(name ="loginRequest", value = "登录请求参数", required = true, dataType = "LoginRequest", dataTypeClass = LoginRequest.class)
    public IResultResponse<LoginResultVO> login(@RequestBody LoginRequest loginRequest) {
        LoggerUtils.debug(getLogger(), "正在登录,请求的信息为--username:[{}]--password:[{}]--code:[{}].",
                loginRequest.getUsername(), SecurityFilterHelper.filterPassword(loginRequest.getPassword()),
                loginRequest.getCode());
        super.checkRequestVO(loginRequest);
        return loginService.login(loginRequest.getUsername(), loginRequest.getPassword());
    }

    /**
     * 确认登录方法
     * @param request HttpServletRequest
     * @param confirmLoginRequest 确认登录请求对象
     * @return 返回结果
     */
    @PostMapping(value = "/confirm/login", produces = PRODUCES_FORMAT_TYPE)
    @ApiOperation(value = "确认登录接口", notes = "确认登录接口；注：需要先调用“登录接口”")
    public IResultResponse<ConfirmLoginResultVO> confirmLogin(HttpServletRequest request, @RequestBody ConfirmLoginRequest confirmLoginRequest) {
        super.checkRequestVO(confirmLoginRequest);
        IResultResponse<ConfirmLoginResultVO> response = new RestResultResponse<>();
        IResultResponse<IUserInfo> confirmResponse = loginService.confirmLogin(confirmLoginRequest.getUserId(), confirmLoginRequest.getCorpId(),
                confirmLoginRequest.getDeptId(), confirmLoginRequest.getToken());
        response.setStatus(confirmResponse.getStatus());
        response.setMsg(confirmResponse.getMsg());
        if(SUCCESS == confirmResponse.getStatus()) {
            IUserInfo userInfo = confirmResponse.getResult();
            //用户信息放到session中
            HttpRequestHelper.setUserInfoToSession(request, userInfo);

            ConfirmLoginResultVO confirmLoginResult = new ConfirmLoginResultVO();
            confirmLoginResult.setUserId(userInfo.getUserId());
            confirmLoginResult.setCorpUserId(userInfo.getCorpUserId());
            confirmLoginResult.setCorpId(userInfo.getCorpId());
            confirmLoginResult.setDeptId(userInfo.getDeptId());
            confirmLoginResult.setCorpName(userInfo.getCorpName());
            confirmLoginResult.setDeptName(userInfo.getDeptName());
            confirmLoginResult.setAvatar(userInfo.getAvatar());
            confirmLoginResult.setLogo(userInfo.getLogo());
            confirmLoginResult.setNickname(userInfo.getNickname());
            confirmLoginResult.setToken(userInfo.getToken());
            response.setResult(confirmLoginResult);
        }
        return response;
    }

}
