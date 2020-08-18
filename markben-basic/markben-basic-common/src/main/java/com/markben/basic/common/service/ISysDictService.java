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
     * 获取所有数据字典信息
     * @return 返回数据字典实体列表
     */
    List<TSysDict> findAll();

}
