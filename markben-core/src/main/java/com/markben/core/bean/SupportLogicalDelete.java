package com.markben.core.bean;

/**
 * 逻辑删除接口
 * @author 乌草坡
 * @since 0.0.1
 */
public interface SupportLogicalDelete {

    /**
     * 获取是否删除的值；
     * @return 返回是否删除的值；1--表示删除；0--表示未删除
     */
    Integer getIsDelete();

    /**
     * 设置是否删除的标识
     * @param isDelete 1--表示删除；0--表示未删除
     */
    void setIsDelete(Integer isDelete);

}
