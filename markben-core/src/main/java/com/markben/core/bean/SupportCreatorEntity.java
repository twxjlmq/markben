package com.markben.core.bean;

/**
 * 含有创建人的实体类
 * @author 乌草坡
 * @since 0.0.1
 */
public interface SupportCreatorEntity extends PKStringEntity {

    /**
     * 设置创建人
     * @param creator 创建人ID
     */
    void setCreator(String creator);

    /**
     * 获取创建人
     * @return 返回创建人的ID
     */
    String getCreator();

}
