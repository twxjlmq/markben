package com.markben.basic.rest.vo.dict;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;

/**
 * 数据字典详情信息
 * @author 乌草坡
 * @since 1.0.0
 */
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DictDetailVO extends DictItemVO {

    @ApiModelProperty(value = "租户ID")
    private String tenantId;

    @ApiModelProperty(value = "租户的用户ID")
    private String tenantUserId;

    public DictDetailVO() {
        super();
    }

    public DictDetailVO(String id, String parentId, String name, Integer sortOrder, String value, Integer state, String tenantId, String tenantUserId) {
        super(id, parentId, name, sortOrder, value, state);
        this.tenantId = tenantId;
        this.tenantUserId = tenantUserId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantUserId() {
        return tenantUserId;
    }

    public void setTenantUserId(String tenantUserId) {
        this.tenantUserId = tenantUserId;
    }
}
