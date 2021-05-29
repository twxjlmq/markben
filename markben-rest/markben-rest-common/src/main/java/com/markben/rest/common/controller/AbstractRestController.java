package com.markben.rest.common.controller;

import com.markben.beans.enums.MarkbenStatusEnums;
import com.markben.beans.response.BaseResponse;
import com.markben.beans.validator.BeanValidatorFactory;
import com.markben.beans.validator.BeanValidator;
import com.markben.common.enable.Checkable;
import com.markben.rest.common.helper.RestCommonHelper;
import com.markben.rest.common.vo.BaseVO;

/**
 * 抽象REST接口控制器类
 * @author 乌草坡
 * @since 0.0.1
 */
public abstract class AbstractRestController extends AbstractBaseController {

    /**
     * JSON数据格式
     */
    protected static final String PRODUCES_JSON = "application/json; charset=UTF-8";

    /**
     * XML数据格式
     */
    protected static final String PRODUCES_XML = "application/xml; charset=UTF-8";

    /**
     * 生产格式类型；默认为JSON数据格式
     */
    protected static final String PRODUCES_FORMAT = PRODUCES_JSON;

    /**
     * 成功状态码
     */
    protected static final int SUCCESS = MarkbenStatusEnums.SUCCESS.getStatus();

    /**
     * 成功状态码对于的信息
     */
    protected static final String SUCCESS_MSG = MarkbenStatusEnums.SUCCESS.getMsg();

    private static BeanValidator beanValidator = BeanValidatorFactory.defaultValidator().build();

    public BeanValidator getBeanValidator() {
        return beanValidator;
    }

    /**
     * 验证请求对象
     * @param baseVO 请求对象
     */
    protected void checkRequestVO(BaseVO baseVO) {
        if(baseVO instanceof Checkable) {
            getBeanValidator().validate((Checkable)baseVO);
        }
    }

    /**
     * 设置成功结果
     * @param response 响应对象
     */
    protected void setSuccessResult(BaseResponse response) {
        RestCommonHelper.setSuccessResult(response);
    }

}
