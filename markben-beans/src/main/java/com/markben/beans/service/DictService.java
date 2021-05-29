package com.markben.beans.service;

import com.markben.beans.entity.TSysDict;
import com.markben.core.service.MgrService;

import java.util.List;

/**
 * 数据字典服务类接口
 * @author 乌草坡
 * @since 0.0.1
 */
public interface DictService extends MgrService<TSysDict> {

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
