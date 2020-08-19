package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.basic.common.enums.ResourceFunType;
import com.markben.common.enums.YesOrNoType;
import com.markben.core.bean.AbstractBaseTreeEntity;
import com.markben.core.bean.ITenantEntity;

/**
 * 系统资源实体类
 * @autor 乌草坡
 * @since 1.0
 */
@TableName(value = "t_sys_resource")
public class TSysResource extends AbstractBaseTreeEntity implements ITenantEntity {

    private String corpId;

    private String restUrl;

    /**
     * 是否功能菜单（按钮）
     * 1 -- 是
     * 0 -- 否
     */
    private Integer isFun = YesOrNoType.NO.getIndex();

    /**
     * 功能类型
     */
    private String funType = ResourceFunType.LIST.getValue();

    /**
     * 是否需要授权
     * 1--需要
     * 0--不需要
     */
    private Integer isAuth = YesOrNoType.YES.getIndex();

    /**
     * 企业用户ID；
     * 该直为{@link TSysCorpUser}实体类中的ID值，
     * 通过该字段与{@link TSysCorpUser}数据表关联
     */
    private String corpUserId;

    @Override
    public String getCorpId() {
        return corpId;
    }

    @Override
    public void setCorpId(String corpId) {
        this.corpId = corpId;
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
        return isAuth;
    }

    public void setIsAuth(Integer isAuth) {
        this.isAuth = isAuth;
    }

    public String getCorpUserId() {
        return corpUserId;
    }

    public void setCorpUserId(String corpUserId) {
        this.corpUserId = corpUserId;
    }

    public String getFunType() {
        return funType;
    }

    public void setFunType(String funType) {
        this.funType = funType;
    }
}
