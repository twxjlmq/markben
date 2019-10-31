package com.markben.core.entity;

import java.util.Date;

/**
 * 创建时间---接口；
 * 如果实体类有创建日期字段，需要系统自动生成创建时间，那么实现该接口
 * @author 乌草坡
 * @version 1.0
 */
public interface ICreateTime {

	/**
	 * 获取创建时间
	 * @return 返回创建时间
	 */
	Date getCreateTime();
	
	/**
	 * 设置创建时间
	 * @param createTime 创建时间
	 */
	void setCreateTime(Date createTime);

}
