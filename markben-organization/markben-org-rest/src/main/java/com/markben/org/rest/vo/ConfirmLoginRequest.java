package com.markben.org.rest.vo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.markben.common.enable.Checkable;
import com.markben.rest.common.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * 确认登录请求对象
 * @author 乌草坡
 * @since 0.0.1
 */
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ConfirmLoginRequest implements BaseVO, Checkable {

    @ApiModelProperty(value = "用户ID", required = true)
    @NotEmpty
    private String userId;

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
