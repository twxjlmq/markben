package com.markben.core.service.event;

/**
 * 回调类型的属性
 * @author 乌草坡
 * @since 0.0.1
 */
public class CallbackTypeProperty {

    /**
     * Mapper对象名称
     */
    private String enhanceMapperName;
    
    /**
     * 值类型名称
     */
    private String valueTypeName;
    
    public CallbackTypeProperty() {

    }
    
    public CallbackTypeProperty(String enhanceMapperName, String valueTypeName) {
        this.enhanceMapperName = enhanceMapperName;
        this.valueTypeName = valueTypeName;
    }

    public String getEnhanceMapperName() {
        return enhanceMapperName;
    }

    public void setEnhanceMapperName(String enhanceMapperName) {
        this.enhanceMapperName = enhanceMapperName;
    }

    public String getValueTypeName() {
        return valueTypeName;
    }

    public void setValueTypeName(String valueTypeName) {
        this.valueTypeName = valueTypeName;
    }
}
