package com.markben.rest.common.controller;

import com.markben.beans.enums.MarkbenStatusEnums;
import com.markben.beans.response.IBaseResponse;
import com.markben.beans.validator.BeanValidatorFactory;
import com.markben.beans.validator.IBeanValidator;
import com.markben.common.enable.ICheckable;
import com.markben.rest.common.vo.IBaseVO;

/**
 * 抽象API控制器类
 * @author 乌草坡
 * @since 1.0
 */
public abstract class AbstractApiController extends AbstractBaseController {

    /**
     * 返回JSON数据
     */
    protected static final String PRODUCES_JSON = "application/json; charset=UTF-8";

    private static IBeanValidator beanValidator = BeanValidatorFactory.defaultValidator().build();

    public IBeanValidator getBeanValidator() {
        return beanValidator;
    }

    /**
     * 验证请求对象
     * @param baseVO 请求对象
     */
    protected void checkRequestVO(IBaseVO baseVO) {
        if(baseVO instanceof ICheckable) {
            getBeanValidator().validate((ICheckable)baseVO);
        }
    }

    /**
     * 设置成功结果
     * @param response 响应对象
     */
    protected void setSuccessResult(IBaseResponse response) {
        if(null != response) {
            response.setStatus(MarkbenStatusEnums.SUCCESS.getStatus());
            response.setMsg(MarkbenStatusEnums.SUCCESS.getMsg());
        }
    }

}
