package com.markben.core.entity.query;

import com.markben.core.helper.PageHelper;

import java.util.List;

/**
 * LIST分页对象
 * @author 乌草坡
 * @since 1.0
 * @param <T>
 */
public class ListPaginate<T> {

    private List<T> datas;
    
    private int pageSize;
    
    private int total;
    
    private int page = 0;
    
    private int totalPage;

    public ListPaginate(List<T> datas, int pageSize) {
        super();
        this.datas = datas;
        this.pageSize = pageSize;
        this.total = datas.size();
        //计算总页数
        this.totalPage = PageHelper.getTotalPage(total, pageSize);
    }

    /**
     * 判断是否有下一页
     * @return 如果有下一页返回true；否则返回false
     */
    public boolean hasNextPage() {
        return (this.totalPage > 0 && (page < totalPage));
    }
    
    /**
     * 获取下一页数据
     * @return 返回下一页数据
     */
    public void next() {
        this.page++;
    }

    /**
     * 获取当前页的数据
     * @return 返回数据
     */
    public List<T> getDatas() {
        int fromIndex = (this.page - 1) * this.pageSize;
        int toIndex = (this.page == this.totalPage) ? this.total : this.page * this.pageSize; 
        return this.datas.subList(fromIndex, toIndex);
    }

    /**
     * 获取当前页数
     * @return 返回当前页
     */
    public int getPage() {
        return this.page;
    }
    
    /**
     * 获取总条数
     * @return 返回总条数
     */
    public int getTotal() {
        return this.total;
    }

    /**
     * 获取总页数
     * @return 返回总页数
     */
    public int getTotalPage() {
        return this.totalPage;
    }
}
