package com.markben.basic.rest.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.common.enums.YesNoType;
import com.markben.core.bean.AbstractSupportTreeEntity;
import com.markben.core.bean.SupportCreatorEntity;

/**
 * 系统资源实体类
 * @author 乌草坡
 * @since 0.0.1
 */
@TableName(value = "t_sys_resource")
public class TSysResource extends AbstractSupportTreeEntity implements SupportCreatorEntity {

    private String restUrl;

    /**
     * 是否功能菜单（按钮）
     * 1 -- 是
     * 0 -- 否
     */
    private Integer isFun = YesNoType.NO.getIndex();

    /**
     * 资源是否分组名称；分组的话，restUrl等属性值为空
     */
    private Integer isGroup = YesNoType.NO.getIndex();

    /**
     * 功能类型；
     */
    private String funType;

    /**
     * 是否需要授权;默认为1；但如果<code>isGroup</code>属性为1时，其值为0；
     * 1--需要
     * 0--不需要
     */
    private Integer isAuth = YesNoType.YES.getIndex();

    /**
     * 租户用户ID；
     * 该直为{@link TSysUser}实体类中的ID值，
     * 通过该字段与{@link TSysUser}数据表关联
     */
    private String creator;

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
        if(YesNoType.YES.getIndex() == this.isGroup) {
            isAuth = YesNoType.NO.getIndex();
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
    public String getCreator() {
        return creator;
    }

    @Override
    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getFunType() {
        return funType;
    }

    public void setFunType(String funType) {
        this.funType = funType;
    }
}
