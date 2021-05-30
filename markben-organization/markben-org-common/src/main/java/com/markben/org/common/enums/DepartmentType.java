package com.markben.org.common.enums;

/**
 * 部门类型
 * @author 乌草坡
 * @since 0.0.1
 */
public enum DepartmentType {

    /**
     * 1 --- department --- 部门
     */
    DEPT(1, "department"),

    /**
     * 2 --- group --- 组（小组）
     */
    GROUP(2, "group");

    private int index;

    private String value;

    DepartmentType(int index, String value) {
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
    public static DepartmentType getObjectByValue(String value) {
        DepartmentType orgType = null;
        if(null == value) {
            return null;
        }
        for(DepartmentType type : DepartmentType.values()) {
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
    public static DepartmentType getObjectByIndex(int index) {
        DepartmentType orgType = null;
        if(0 == index) {
            return null;
        }
        for(DepartmentType type : DepartmentType.values()) {
            if(index  == type.getIndex()) {
                orgType = type;
                break;
            }
        }
        return orgType;
    }
}
