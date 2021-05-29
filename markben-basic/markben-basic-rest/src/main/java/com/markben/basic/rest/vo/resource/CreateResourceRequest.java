package com.markben.basic.rest.vo.resource;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.markben.beans.validator.hibernate.IntValueContain;
import com.markben.common.constant.MarkbenConstant;
import com.markben.common.enable.Checkable;
import com.markben.common.enums.YesOrNoType;
import com.markben.rest.common.vo.AbstractRestRequest;
import com.markben.rest.common.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * 创建资源请求对象
 * @author 乌草坡
 * @since 0.0.1
 */
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CreateResourceRequest extends AbstractRestRequest implements BaseVO, Checkable {

    @ApiModelProperty(value = "名称", required = true)
    @NotEmpty
    private String name;

    @ApiModelProperty(value = "父ID；为空时使用默认值：0")
    private String parentId = MarkbenConstant.TREE_ROOT_ID;

    @ApiModelProperty(value = "状态，1--有效；0--无效；默认为：1")
    @IntValueContain(value = {0, 1})
    private Integer state;

    @ApiModelProperty(value = "排序序号，默认为0")
    private Integer sortOrder = 0;

    @ApiModelProperty(value = "REST接口地址", required = true)
    @NotEmpty
    private String restUrl;

    @ApiModelProperty(value = "是否为分组资源；1--是；0--否;默认为0")
    private Integer isGroup = YesOrNoType.NO.getIndex();

    @ApiModelProperty(value = "是否是功能按钮；1--是；0--否;默认为0")
    private Integer isFun = YesOrNoType.NO.getIndex();

    @ApiModelProperty(value = "功能类型；如：add--添加；update-更新；delete--删除等")
    private String funType;

    @ApiModelProperty(value = "是否需要授权；1--需要；0--不需要；默认为1")
    private Integer isAuth = YesOrNoType.YES.getIndex();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getRestUrl() {
        return restUrl;
    }

    public void setRestUrl(String restUrl) {
        this.restUrl = restUrl;
    }

    public Integer getIsFun() {
        return isFun;
    }

    public void setIsFun(Integer isFun) {
        this.isFun = isFun;
    }

    public Integer getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(Integer isGroup) {
        this.isGroup = isGroup;
    }

    public Integer getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(Integer isAuth) {
        this.isAuth = isAuth;
    }

    public String getFunType() {
        return funType;
    }

    public void setFunType(String funType) {
        this.funType = funType;
    }
}
