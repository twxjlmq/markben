package com.markben.beans.response;

/**
 * 分页返回对象接口
 * @author 乌草坡
 * @since 1.0
 */
public interface IPaginateResponse<T> extends ICollectionResponse<T> {

    /**
     * 总的记录数
     * @return 返回总记录数
     */
    Long getTotal();

    /**
     * 设置总记录数
     * @param total 总记录数
     */
    void setTotal(Long total);

    /**
     * 获取显示条数
     * @return 返回显示条数
     */
    Integer getSize();

    /**
     * 设置显示条数
     * @param size 显示条数
     */
    void setSize(Integer size);

    /**
     * 获取总分页数
     * @return 返回总分页数
     */
    Integer getTotalPage();

    /**
     * 设置总分页数
     * @param totalPage 总分页数
     */
    void setTotalPage(Integer totalPage);
}
