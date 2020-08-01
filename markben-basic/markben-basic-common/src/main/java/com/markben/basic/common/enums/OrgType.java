package com.markben.basic.common.enums;

/**
 * 组织机构类型
 * @author 乌草坡
 * @since 1.0
 */
public enum OrgType {

    /**
     * 1 --- company -- 组织
     */
    ORG(1, "company"),

    /**
     * 2 --- department --- 部门
     */
    DEPT(2, "department"),

    /**
     * 3 --- group --- 组（小组）
     */
    GROUP(3, "group");

    private int index;

    private String value;

    OrgType(int index, String value) {
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
     * 获取组织机构类型对象，通过指定的值
     * @param value 值
     * @return 如果有值对应的类型则返回对应类型的对象；否则返回null
     */
    public static OrgType getObjectByValue(String value) {
        OrgType orgType = null;
        if(null == value) {
            return null;
        }
        for(OrgType type : OrgType.values()) {
            if(value.equals(type.getValue())) {
                orgType = type;
                break;
            }
        }
        return orgType;
    }

    /**
     * 获取组织机构类型对象，通过指定的值
     * @param index 值
     * @return 如果有值对应的类型则返回对应类型的对象；否则返回null
     */
    public static OrgType getObjectByIndex(int index) {
        OrgType orgType = null;
        if(0 == index) {
            return null;
        }
        for(OrgType type : OrgType.values()) {
            if(index  == type.getIndex()) {
                orgType = type;
                break;
            }
        }
        return orgType;
    }
}
