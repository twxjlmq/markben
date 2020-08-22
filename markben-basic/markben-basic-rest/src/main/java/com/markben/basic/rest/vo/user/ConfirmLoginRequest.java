package com.markben.basic.rest.vo.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.markben.common.enable.ICheckable;
import com.markben.rest.common.vo.IBaseVO;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * 确认登录请求对象
 * @author 乌草坡
 * @since 1.0
 */
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ConfirmLoginRequest implements IBaseVO, ICheckable {

    @ApiModelProperty(value = "用户ID", required = true)
    @NotEmpty
    private String userId;

    @ApiModelProperty(value = "企业ID或组织ID", required = true)
    @NotEmpty
    private String corpId;

    @ApiModelProperty(value = "部门ID", required = true)
    @NotEmpty
    private String deptId;

    @ApiModelProperty(value = "TOKEN", required = true)
    @NotEmpty
    private String token;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
