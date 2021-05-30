package com.markben.basic.rest.enums;

/**
 * 版本类型
 * @author 乌草坡
 * @since 0.0.1
 */
public enum VersionType {

    /**
     * 1 --- pc -- PC端
     */
    PC(1, "pc"),

    /**
     * 2 --- mobile --- 移动端
     */
    MOBILE(2, "mobile"),

    /**
     * 3 --- restful --- REST接口
     */
    REST(3, "restful"),

    /**
     * 9 --- all --- 全部
     */
    ALL(9, "all");

    private int index;

    private String value;

    VersionType(int index, String value) {
        this.index = index;
        this.value = value;
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

    /**
     * 获取版本类型对象，通过指定的值
     * @param value 值
     * @return 如果有值对应的类型则返回对应类型的对象；否则返回null
     */
    public static VersionType getObjectByValue(String value) {
        VersionType versionType = null;
        if(null == value) {
            return null;
        }
        for(VersionType type : VersionType.values()) {
            if(value.equals(type.getValue())) {
                versionType = type;
                break;
            }
        }
        return versionType;
    }

    /**
     * 获取版本类型对象，通过指定的值
     * @param index 值
     * @return 如果有值对应的类型则返回对应类型的对象；否则返回null
     */
    public static VersionType getObjectByIndex(int index) {
        VersionType versionType = null;
        if(0 == index) {
            return null;
        }
        for(VersionType type : VersionType.values()) {
            if(index  == type.getIndex()) {
                versionType = type;
                break;
            }
        }
        return versionType;
    }
}
