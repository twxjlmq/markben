package com.markben.rest.common.controller;

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


}
