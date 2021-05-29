package com.markben.basic.common.enums;

import com.markben.common.utils.StringUtils;

/**
 * 资源功能类型
 * @author 乌草坡
 * @since 0.0.1
 */
public enum ResourceFunType {

    /**
     * null -- “”
     * 没有资源类型
     */
    NONE(null, ""),

    /**
     * list -- 列表
     */
    LIST("list", "列表"),

    /**
     * add -- 添加
     */
    ADD("add", "添加"),

    /**
     * update -- 更新
     */
    UPDATE("update", "更新"),

    /**
     * delete -- 删除
     */
    DELETE("delete", "删除"),

    /**
     * refresh -- 刷新
     */
    REFRESH("refresh", "刷新"),

    /**
     * export -- 导出
     */
    EXPORT("export", "导出"),

    /**
     * import -- 导入
     */
    IMPORT("import", "导入");

    private String value;

    private String text;

    ResourceFunType(String value, String text) {
        this.value = value;
        this.text = text;
    }

    /**
     * 获取资源功能类型对象
     * @param value 功能类型值
     * @return 返回资源功能类型对象
     */
    public static ResourceFunType getObject(String value) {
        if(StringUtils.isEmpty(value)) {
            return null;
        }
        ResourceFunType funType = null;
        for(ResourceFunType type : ResourceFunType.values()) {
            if(value.equals(type.getValue())) {
                funType = type;
                break;
            }
        }
        return funType;
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
