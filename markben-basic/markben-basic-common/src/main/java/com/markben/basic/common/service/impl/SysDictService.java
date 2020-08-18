package com.markben.basic.common.service.impl;

import com.markben.basic.common.entity.TSysDict;
import com.markben.basic.common.service.ISysDictService;
import com.markben.core.service.EnhanceServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据字典实现类
 * @author 乌草坡
 * @since 1.0
 */
@Service
public class SysDictService extends EnhanceServiceImpl<TSysDict> implements ISysDictService {

    @Override
    public List<TSysDict> findAll() {
        return super.list(query().orderByAsc("sort_order"));
    }
}
