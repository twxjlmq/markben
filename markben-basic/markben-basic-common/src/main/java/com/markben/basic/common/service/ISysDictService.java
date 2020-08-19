package com.markben.basic.common.service;

import com.markben.basic.common.entity.TSysDict;
import com.markben.beans.response.ICollectionResponse;
import com.markben.core.service.IMgrService;

import java.util.List;

/**
 * @author 乌草坡
 * @since 1.0
 */
public interface ISysDictService extends IMgrService<TSysDict> {

    /**
     * 获取所有数据字典实体列表
     * @return 返回数据字典实体列表
     */
    List<TSysDict> findAll();

    /**
     * 获取有效的数据字典实体列表
     * @return 返回有效的数据字典实体列表
     */
    List<TSysDict> findAllByValid();

    /**
     * 获取数据字典子项通过ID
     * @param id ID
     * @return 返回数据字典实体列表
     */
    List<TSysDict> getSubItemById(String id);

    /**
     * 获取数据字典子项通过业务值
     * @param value 业务值
     * @return 返回数据字典实体列表
     */
    List<TSysDict> getItemByValue(String value);

}
