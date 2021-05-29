package com.markben.beans.response;

import java.util.Collection;

/**
 * 数据集合返回对象接口
 * @author 乌草坡
 * @since 0.0.1
 */
public interface CollectionResponse<T> extends BaseResponse {

    /**
     * 获取数据集合
     * @return 返回集合
     */
    Collection<T> getData();

    /**
     * 设置数据集合
     * @param data 数据集合
     */
    void setData(Collection<T> data);

}
