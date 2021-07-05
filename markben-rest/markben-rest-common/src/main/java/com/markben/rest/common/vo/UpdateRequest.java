package com.markben.rest.common.vo;

/**
 * 更新请求抽象类
 * @author 乌草坡
 * @since 0.0.1
 */
public interface UpdateRequest extends RestRequest {

    /**
     * 获取ID
     * @return 返回ID
     */
    String getId();

    /**
     * 设置ID
     * @param id 设置用以更新的主键ID
     */
    void setId(String id);
}
