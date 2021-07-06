package com.markben.basic.common.bean;

/**
 * @author 乌草坡
 * @since 0.0.1
 */
public interface MenuItem {

    String getId();

    String getParentId();

    String getName();

    Integer getSortOrder();

    Integer getState();

    String getStateText();

    String getCreateTime();

    String getResourceId();

    String getResourceName();

    String getMenuType();

    String getMenuTypeText();

    String getPcIcon();

    String getMobileIcon();

}
