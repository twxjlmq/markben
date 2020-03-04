package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.common.enums.YesOrNoType;
import com.markben.core.bean.BaseTreeEntity;
import com.markben.core.bean.ICreateTime;

import java.util.Date;

/**
 * 系统资源实体类
 * @autor 乌草坡
 * @since 1.0
 */
@TableName(value = "t_sys_resource")
public class TSysResource extends BaseTreeEntity implements ICreateTime {

    private String restUrl;

    private String mobileUrl;

    private String pcUrl;

    private Integer isFun;

    private Integer state = YesOrNoType.YES.getIndex();

    private Integer isAuth;

    private String corpUserId;

    private Date createTime;

    public String getRestUrl() {
        return restUrl;
    }

    public void setRestUrl(String restUrl) {
        this.restUrl = restUrl;
    }

    public String getMobileUrl() {
        return mobileUrl;
    }

    public void setMobileUrl(String mobileUrl) {
        this.mobileUrl = mobileUrl;
    }

    public String getPcUrl() {
        return pcUrl;
    }

    public void setPcUrl(String pcUrl) {
        this.pcUrl = pcUrl;
    }

    public Integer getIsFun() {
        return isFun;
    }

    public void setIsFun(Integer isFun) {
        this.isFun = isFun;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
