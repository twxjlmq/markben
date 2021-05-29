package com.markben.basic.rest.helper;

import com.markben.beans.entity.TSysDict;
import com.markben.basic.rest.vo.dict.CreateDictRequest;
import com.markben.common.utils.StringUtils;
import com.markben.rest.common.vo.UpdateRequest;

/**
 * 数据字典辅助类
 * @author 乌草坡
 * @since 0.0.1
 */
public class DictHelper {

    /**
     * 转换对象
     * @param createRequest
     * @return
     */
    public static TSysDict convert(CreateDictRequest createRequest) {
        TSysDict dict = new TSysDict();
        dict.setValue(createRequest.getValue());
        if(StringUtils.isInteger(dict.getValue())) {
            dict.setIntValue(Integer.parseInt(dict.getValue()));
        }
        dict.setName(createRequest.getName());
        if(StringUtils.isNotEmpty(createRequest.getParentId())) {
            dict.setParentId(createRequest.getParentId());
        }
        dict.setSortOrder(createRequest.getSortOrder());
        dict.setState(createRequest.getState());

        if(createRequest instanceof UpdateRequest) {
            UpdateRequest updateRequest = (UpdateRequest) createRequest;
            dict.setId(updateRequest.getId());
        }
        return dict;
    }

}
