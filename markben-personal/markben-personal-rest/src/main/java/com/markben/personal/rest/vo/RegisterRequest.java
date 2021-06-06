package com.markben.personal.rest.vo;

import com.markben.rest.common.vo.RestRequest;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * 用户注册请求类
 * @author 乌草坡
 * @since 0.0.1
 */
public class RegisterRequest extends UserVO implements RestRequest {

    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty(message = "密码不能为空")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
