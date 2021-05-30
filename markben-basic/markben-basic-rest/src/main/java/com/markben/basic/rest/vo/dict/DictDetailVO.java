package com.markben.basic.rest.vo.dict;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;

/**
 * 数据字典详情信息
 * @author 乌草坡
 * @since 0.0.1
 */
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DictDetailVO extends DictItemVO {

    @ApiModelProperty(value = "租户的用户ID")
    private String tenantUserId;

    public DictDetailVO() {
        super();
    }

    public DictDetailVO(String id, String parentId, String name, Integer sortOrder, String value, Integer state,  String tenantUserId) {
        super(id, parentId, name, sortOrder, value, state);
        this.tenantUserId = tenantUserId;
    }

    public String getTenantUserId() {
        return tenantUserId;
    }

    public void setTenantUserId(String tenantUserId) {
        this.tenantUserId = tenantUserId;
    }
}
