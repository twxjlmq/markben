package com.markben.basic.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markben.basic.common.enums.DepartmentType;
import com.markben.core.bean.AbstractSupportTreeEntity;
import com.markben.multi.tenancy.entity.SupportTenantEntity;

/**
 * 系统部门实体类
 * @author 乌草坡
 * @since 0.0.1
 */
@TableName(value = "t_sys_department")
public class TSysDepartment extends AbstractSupportTreeEntity implements SupportTenantEntity {

    private String tenantId;

    /**
     * 类型
     */
    private String type = DepartmentType.DEPT.getValue();

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

    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
