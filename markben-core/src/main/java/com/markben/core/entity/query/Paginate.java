package com.markben.core.entity.query;

import com.markben.core.helper.PageHelper;

import java.util.List;

/**
 * 分页对象
 * @author 乌草坡
 * @since 1.0
 * @param <T>
 */
public class Paginate<T> {

	/** 分页参数 **/
	private int pageNo = 1;

	private int pageSize;

	private long totalCount;

	private List<T> datas;

	/**
	 * 判断是否还有下一页.
	 * @return 如果有下一页则返回：true；否则返回：false
	 */
	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	/**
	 * 取得下页的页号, 序号从1开始.
	 * 当前页为尾页时仍返回尾页序号.
	 * @return 返回下一页；如果没有下一页则返回当前页
	 */
	public int getNextPage() {
		if (isHasNext()) {
			return pageNo + 1;
		} else {
			return pageNo;
		}
	}

	/**
	 * 是否还有上一页.
	 * @return 判断是否前一页
	 */
	public boolean isHasPrev() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * 取得上页的页号, 序号从1开始.
	 * 当前页为首页时返回首页序号.
	 * @return 返回前一页；如果没有前一页，则返回当前页
	 */
	public int getPrePage() {
		if (isHasPrev()) {
			return pageNo - 1;
		} else {
			return pageNo;
		}
	}

	/**
	 * 获取页码
	 * @return 返回当前页码
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 * @param pageNo 页码
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}


	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	/**
	 * 获取总的页面数
	 * @return 返回总页数
	 */
	public int getTotalPages() {
		return PageHelper.getTotalPage(getTotalCount(), getPageSize());
	}

}
