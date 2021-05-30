package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.basic.common.enums.VersionType;
import com.markben.core.bean.AbstractCommonPropEntity;

/**
 * 版本信息实体类
 * @author 乌草坡
 * @since 0.0.1
 */
@TableName(value = "t_sys_version")
public class TSysVersion extends AbstractCommonPropEntity {

    /**
     * 版本
     */
    private String version;

    private Long numVersion = 0L;

    private String type = VersionType.ALL.getValue();

    /**
     * 描述
     */
    private String describe;

    private String creator;

    /**
     * 更新日期
     */
    private String updateDate;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getNumVersion() {
        return numVersion;
    }

    public void setNumVersion(Long numVersion) {
        this.numVersion = numVersion;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
