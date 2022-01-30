package com.markben.personal.rest.vo;

import com.markben.restful.common.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * 用户VO类
 * @author 乌草坡
 * @since 0.0.1
 */
public class UserVO implements BaseVO {

    @ApiModelProperty(value = "用户名", required = true)
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "手机号", required = true)
    @NotEmpty(message = "手机号不能为空")
    private String mobile;

    @ApiModelProperty(value = "匿名", required = true)
    @NotEmpty(message = "匿名不能为空")
    private String nickname;

    @ApiModelProperty(value = "备注")
    private String remarks;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
