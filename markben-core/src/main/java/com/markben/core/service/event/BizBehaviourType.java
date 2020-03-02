package com.markben.core.service.event;

/**
 * 业务的行为类型
 * @author 乌草坡
 * @since 1.0
 */
public enum BizBehaviourType {

    /**
     * save -- 保存
     */
    SAVE(1, "save", "保存"),
    
    /**
     * update -- 更新
     */
    UPDATE(2, "update", "更新"),
    
    /**
     * delete -- 删除
     */
    DELETE(3, "delete", "删除"),
    
    /**
     * query -- 查询
     */
    QUERY(4, "query", "查询");
    
    private int index;
    
    private String value;
    
    private String text;
    
    private BizBehaviourType(int index, String value, String text) {
        this.index = index;
        this.value = value;
        this.text = text;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
}
