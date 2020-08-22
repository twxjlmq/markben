package com.markben.basic.rest.vo.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.markben.common.enums.YesOrNoType;
import com.markben.rest.common.vo.IBaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 登录接口
 * @author 乌草坡
 * @since 1.0
 */
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class LoginResultVO implements IBaseVO {

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "TOKEN")
    private String token;

    public LoginResultVO() {

    }

    public LoginResultVO(String userId) {
        this.userId = userId;
    }

    @ApiModelProperty(value = "所在组织列表")
    private List<SimpleCorpInfoVO> corps;

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

    public List<SimpleCorpInfoVO> getCorps() {
        return corps;
    }

    public void setCorps(List<SimpleCorpInfoVO> corps) {
        this.corps = corps;
    }

    @JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class SimpleCorpInfoVO {

        @ApiModelProperty(value = "组织ID或企业ID")
        private String corpId;

        @ApiModelProperty(value = "组织名称或企业名称")
        private String name;

        @ApiModelProperty(value = "LOGO地址")
        private String logo;

        @ApiModelProperty(value = "是否是默认组织或企业；1--是；0--否")
        private Integer isDefault = YesOrNoType.NO.getIndex();

        @ApiModelProperty(value = "所在部门列表")
        private List<SimpleOrgInfoVO> orgList;

        public String getCorpId() {
            return corpId;
        }

        public void setCorpId(String corpId) {
            this.corpId = corpId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public Integer getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(Integer isDefault) {
            this.isDefault = isDefault;
        }

        public List<SimpleOrgInfoVO> getOrgList() {
            return orgList;
        }

        public void setOrgList(List<SimpleOrgInfoVO> orgList) {
            this.orgList = orgList;
        }
    }

    @JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class SimpleOrgInfoVO {

        public SimpleOrgInfoVO() {
        }

        public SimpleOrgInfoVO(String deptId, String name) {
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
