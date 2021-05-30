package com.markben.basic.rest.service.impl;

import com.markben.basic.rest.entity.TSysDict;
import com.markben.basic.rest.service.DictService;
import com.markben.common.enums.YesNoType;
import com.markben.common.utils.CollectionUtils;
import com.markben.common.utils.StringUtils;
import com.markben.core.service.EnhanceServiceImpl;
import com.markben.core.utils.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据字典服务实现类
 * @author 乌草坡
 * @since 0.0.1
 */
@Service
public class DictServiceImpl extends EnhanceServiceImpl<TSysDict> implements DictService {

    @Override
    public List<TSysDict> findAll() {
        return super.list(query().orderByAsc(EntityUtils.toDbField("sortOrder")));
    }

    @Override
    public List<TSysDict> findAllByValid() {
        return super.list(query().eq("state", YesNoType.YES.getIndex()).orderByAsc(EntityUtils.toDbField("sortOrder")));
    }

    @Override
    public List<TSysDict> getSubItemById(String id) {
        if(StringUtils.isEmpty(id)) {
            return Collections.EMPTY_LIST;
        }
        List<TSysDict> list = findAllByValid();
        if(CollectionUtils.isEmpty(list)) {
            return Collections.EMPTY_LIST;
        }
        return list.stream().filter(d -> d.getParentId().equals(id)).collect(Collectors.toList());
    }

    @Override
    public List<TSysDict> getItemByValue(String value) {
        if(StringUtils.isEmpty(value)) {
            return Collections.EMPTY_LIST;
        }
        TSysDict dict = super.getOne(query().eq("value", value).getWrapper());
        if(null == dict) {
            return Collections.EMPTY_LIST;
        }
        return getSubItemById(dict.getId());
    }
}
