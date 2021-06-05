package com.markben.personal.rest.controller;

import com.markben.beans.bean.UserInfo;
import com.markben.beans.enums.MarkbenStatusEnums;
import com.markben.beans.response.ResultResponse;
import com.markben.common.utils.LoggerUtils;
import com.markben.common.utils.ObjectUtils;
import com.markben.personal.rest.service.LoginService;
import com.markben.basic.rest.vo.LoginRequest;
import com.markben.personal.rest.vo.LoginResultVO;
import com.markben.rest.common.controller.AbstractRestController;
import com.markben.rest.common.helper.SecurityFilterHelper;
import com.markben.rest.common.response.RestResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * 个人用户REST接口控制器类
 * @author 乌草坡
 * @since 0.0.1
 */
@RestController
@RequestMapping("/rest/personal")
@Api(value = "用户接口", tags = {"用户接口"})
public class RestPersonalController extends AbstractRestController {

    private LoginService loginService;

    public RestPersonalController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 登录接口方法
     * @param loginRequest 登录请求对象
     * @return 返回结果
     */
    @PostMapping(value = "/login", produces = PRODUCES_FORMAT)
    @ApiOperation(value = "登录接口", notes = "登录接口；注：调用登录接口之后需要再次调用“确认登录接口”后，才完成整个登录的过程。")
    public ResultResponse<LoginResultVO> login(HttpServletRequest request, @RequestBody LoginRequest loginRequest) {
        LoggerUtils.debug(getLogger(), "正在登录,请求的信息为--username:[{}]--password:[{}]--code:[{}].",
                loginRequest.getUsername(), SecurityFilterHelper.filterPassword(loginRequest.getPassword()),
                loginRequest.getCode());
        super.checkRequestVO(loginRequest);
        ResultResponse<UserInfo> resp = loginService.login(loginRequest.getUsername(), loginRequest.getPassword());
        ResultResponse<LoginResultVO> rtnResp = new RestResultResponse<>();
        if(resp.getStatus() == MarkbenStatusEnums.SUCCESS.getStatus()) {
            super.setUserInfo2Session(request, resp.getResult());
            Optional<LoginResultVO> resultOpt = ObjectUtils.convertObject(LoginResultVO.class, resp.getResult());
            resultOpt.ifPresent(rtnResp::setResult);
        }
        super.setResponseStatus(resp, rtnResp);
        return rtnResp;

    }

}
