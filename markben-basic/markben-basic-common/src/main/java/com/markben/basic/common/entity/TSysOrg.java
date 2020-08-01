package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.basic.common.enums.OrgType;
import com.markben.core.bean.AbstractTenantEntity;

/**
 * 组织架构实体类
 * @autor 乌草坡 2020-03-04
 * @since 1.0
 */
@TableName(value = "t_sys_org")
public class TSysOrg extends AbstractTenantEntity {

    private String type = OrgType.DEPT.getValue();

    /**
     * 编号
     */
    private String code;

    /**
     * 父序列;如：1111.2222.3333
     */
    private String seqParentId;

    /**
     * 名称序列;如: xxx有限公司>软件部>产品组
     */
    private String seqName;

    private String remarks;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSeqParentId() {
        return seqParentId;
    }

    public void setSeqParentId(String seqParentId) {
        this.seqParentId = seqParentId;
    }

    public String getSeqName() {
        return seqName;
    }

    public void setSeqName(String seqName) {
        this.seqName = seqName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
