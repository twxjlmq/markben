package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.common.enums.YesOrNoType;
import com.markben.core.bean.AbstractBaseTreeEntity;
import com.markben.core.bean.ICreatorEntity;

/**
 * 系统资源实体类
 * @author 乌草坡
 * @since 1.0.0
 */
@TableName(value = "t_sys_resource")
public class TSysResource extends AbstractBaseTreeEntity implements ICreatorEntity {

    private String tenantId;

    private String restUrl;

    /**
     * 是否功能菜单（按钮）
     * 1 -- 是
     * 0 -- 否
     */
    private Integer isFun = YesOrNoType.NO.getIndex();

    /**
     * 资源是否分组名称；分组的话，restUrl等属性值为空
     */
    private Integer isGroup = YesOrNoType.NO.getIndex();

    /**
     * 功能类型；
     */
    private String funType;

    /**
     * 是否需要授权;默认为1；但如果<code>isGroup</code>属性为1时，其值为0；
     * 1--需要
     * 0--不需要
     */
    private Integer isAuth = YesOrNoType.YES.getIndex();

    /**
     * 租户用户ID；
     * 该直为{@link TSysTenantUser}实体类中的ID值，
     * 通过该字段与{@link TSysTenantUser}数据表关联
     */
    private String tenantUserId;

    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
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

    public Integer getIsAuth() {
        if(YesOrNoType.YES.getIndex() == this.isGroup) {
            isAuth = YesOrNoType.NO.getIndex();
        }
        return isAuth;
    }

    public void setIsAuth(Integer isAuth) {
        this.isAuth = isAuth;
    }

    public Integer getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(Integer isGroup) {
        this.isGroup = isGroup;
    }

    @Override
    public String getTenantUserId() {
        return tenantUserId;
    }

    @Override
    public void setTenantUserId(String tenantUserId) {
        this.tenantUserId = tenantUserId;
    }

    public String getFunType() {
        return funType;
    }

    public void setFunType(String funType) {
        this.funType = funType;
    }
}
