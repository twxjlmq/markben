package com.markben.org.rest.vo;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.markben.rest.common.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 登录结果
 * @author 乌草坡
 * @since 0.0.1
 */
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrgLoginResultVO implements BaseVO {

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "TOKEN")
    private String token;

    @ApiModelProperty(value = "所在部门列表")
    private List<SimpleDeptInfoVO> deptList;

    public OrgLoginResultVO() {

    }

    public OrgLoginResultVO(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<SimpleDeptInfoVO> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<SimpleDeptInfoVO> deptList) {
        this.deptList = deptList;
    }

    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class SimpleDeptInfoVO {

        public SimpleDeptInfoVO() {
        }

        public SimpleDeptInfoVO(String deptId, String name) {
            this.deptId = deptId;
            this.name = name;
        }

        @ApiModelProperty(value = "部门ID")
        private String deptId;

        @ApiModelProperty(value = "部门名称")
        private String name;

        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
