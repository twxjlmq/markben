package com.markben.rest.common.controller;

import com.markben.beans.enums.MarkbenStatusEnums;
import com.markben.beans.response.IBaseResponse;
import com.markben.beans.validator.BeanValidatorFactory;
import com.markben.beans.validator.IBeanValidator;
import com.markben.common.enable.ICheckable;
import com.markben.rest.common.helper.RestCommonHelper;
import com.markben.rest.common.vo.IBaseVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 抽象REST接口控制器类
 * @author 乌草坡
 * @since 1.0
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
    protected static final String PRODUCES_FORMAT_TYPE = PRODUCES_JSON;

    /**
     * 成功状态码
     */
    protected static final int SUCCESS = MarkbenStatusEnums.SUCCESS.getStatus();

    /**
     * 成功状态码对于的信息
     */
    protected static final String SUCCESS_MSG = MarkbenStatusEnums.SUCCESS.getMsg();

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
        RestCommonHelper.setSuccessResult(response);
    }

}
