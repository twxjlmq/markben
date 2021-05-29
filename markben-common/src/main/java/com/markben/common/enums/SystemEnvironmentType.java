package com.markben.common.enums;

import com.markben.common.utils.StringUtils;

/**
 * 系统环境类型
 * @author 乌草坡
 * @since 0.0.1
 */
public enum SystemEnvironmentType {

    /**
     * 1 -- development -- 开发环境
     */
    DEVELOPMENT(1,"development", "开发环境"),

    /**
     * 2 -- test -- 测试环境
     */
    TEST(2, "test", "测试环境"),

    /**
     * 3 -- production -- 生产环境
     */
    PRODUCTION(3, "production", "生产环境");

    private Integer index;

    private String value;

    private String text;

    SystemEnvironmentType(Integer index, String value, String text) {
        this.index = index;
        this.value = value;
        this.text = text;
    }

    /**
     * 获取项目环境类型
     * @param index 类型值
     * @return 返回环境类型
     */
    public static SystemEnvironmentType getObject(Integer index) {
        if(null == index) {
            return DEVELOPMENT;
        }
        SystemEnvironmentType environmentType = DEVELOPMENT;
        for(SystemEnvironmentType type : SystemEnvironmentType.values()) {
            if(type.getIndex() == index) {
                environmentType = type;
                break;
            }
        }
        return environmentType;
    }

    /**
     * 获取项目环境类型
     * @param value 类型值
     * @return 返回环境类型
     */
    public static SystemEnvironmentType getObject(String value) {
        if(StringUtils.isEmpty(value)) {
            return DEVELOPMENT;
        }
        SystemEnvironmentType environmentType = DEVELOPMENT;
        for(SystemEnvironmentType type : SystemEnvironmentType.values()) {
            if(type.getValue().equals(value)) {
                environmentType = type;
                break;
            }
        }
        return environmentType;
    }

    /**
     * 判断当前是否是开发环境;
     * 如果是开发环境返回:true；否则返回：false
     * @return 返回true或false
     */
    public boolean isDevelopment() {
        return this.getIndex() == DEVELOPMENT.getIndex();
    }

    /**
     * 判断当前是否是测试环境;
     * 如果是测试环境返回:true；否则返回：false
     * @return 返回true或false
     */
    public boolean isTest() {
        return this.getIndex() == TEST.getIndex();
    }

    /**
     * 判断当前是否是生产环境;
     * 如果是生产环境返回:true；否则返回：false
     * @return 返回true或false
     */
    public boolean isProduction() {
        return this.getIndex() == PRODUCTION.getIndex();
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
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
