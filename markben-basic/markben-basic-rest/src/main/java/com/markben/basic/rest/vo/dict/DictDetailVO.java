package com.markben.basic.rest.vo.dict;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * 数据字典详情信息
 * @author 乌草坡
 * @since 1.0
 */
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DictDetailVO extends DictItemVO {

    private String corpId;

    private String corpUserId;

    public DictDetailVO() {
        super();
    }

    public DictDetailVO(String id, String parentId, String name, Integer sortOrder, String value, Integer state, String corpId, String corpUserId) {
        super(id, parentId, name, sortOrder, value, state);
        this.corpId = corpId;
        this.corpUserId = corpUserId;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getCorpUserId() {
        return corpUserId;
    }

    public void setCorpUserId(String corpUserId) {
        this.corpUserId = corpUserId;
    }
}
