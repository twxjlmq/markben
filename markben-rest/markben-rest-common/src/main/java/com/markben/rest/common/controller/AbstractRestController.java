package com.markben.rest.common.controller;

import com.markben.beans.bean.UserInfo;
import com.markben.beans.enums.MarkbenStatusEnums;
import com.markben.beans.helper.ResponseHelper;
import com.markben.beans.response.BaseResponse;
import com.markben.beans.validator.BeanValidator;
import com.markben.beans.validator.BeanValidatorFactory;
import com.markben.common.enable.Checkable;
import com.markben.core.bean.PKStringEntity;
import com.markben.core.bean.SupportCreatorEntity;
import com.markben.core.bean.SupportLastUpdaterEntity;
import com.markben.core.service.MgrService;
import com.markben.rest.common.response.RestBaseResponse;
import com.markben.rest.common.response.RestResultResponse;
import com.markben.rest.common.vo.BaseVO;
import com.markben.rest.common.vo.IdRequest;
import com.markben.rest.common.vo.RestRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Supplier;

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
        ResponseHelper.setSuccessResult(response);
    }

    /**
     * 设置接口的相应状态
     * @param resultResp 服务接口返回的处理结果对象
     * @param rtnResp 返回到给请求接口的响应对象
     */
    protected void setResponseStatus(BaseResponse resultResp, BaseResponse rtnResp) {
        if(null == resultResp || null == rtnResp) {
            return;
        }
        rtnResp.setStatus(resultResp.getStatus());
        rtnResp.setMsg(resultResp.getMsg());
    }

    /**
     * 设置接口的相应状态
     * @param resultResp 响应对象
     * @param statusEnum 状态枚举对象
     */
    protected void setResponseStatus(BaseResponse resultResp, MarkbenStatusEnums statusEnum) {
        ResponseHelper.setResponseStatus(resultResp, statusEnum);
    }

    /**
     * 创建数据
     * @param request 请求对象
     * @param createRequest 创建请求对象
     * @param mgrService 服务类
     * @param supplier 提供实体对象函数试方法
     * @return 返回结果
     */
    protected RestResultResponse<String> create(HttpServletRequest request, RestRequest createRequest,
                                            MgrService mgrService, Supplier<PKStringEntity> supplier) {
        checkRequestVO(createRequest);
        RestResultResponse<String> resultResp = new RestResultResponse<>();
        UserInfo userInfo = getUserInfoByRequest(request);
        PKStringEntity entity = supplier.get();
        if(null != entity) {
            if(entity instanceof SupportCreatorEntity) {
                SupportCreatorEntity creatorEntity = (SupportCreatorEntity) entity;
                creatorEntity.setCreator(userInfo.getUserId());
            }
            if(mgrService.save(entity)) {
                setSuccessResult(resultResp);
                resultResp.setResult(entity.getId());
            }
        }
        return resultResp;
    }

    /**
     * 更新数据
     * @param request Http请求对象
     * @param updateRequest 更新请求对象
     * @param mgrService 服务类
     * @param supplier 提供实体对象函数试方法
     * @return 返回结果
     */
    protected RestResultResponse<String> update(HttpServletRequest request, RestRequest updateRequest,
                                            MgrService mgrService, Supplier<PKStringEntity> supplier) {
        checkRequestVO(updateRequest);
        RestResultResponse<String> resultResp = new RestResultResponse<>();
        PKStringEntity entity = supplier.get();
        if(null != entity) {
            UserInfo userInfo = getUserInfoByRequest(request);
            if(entity instanceof SupportLastUpdaterEntity) {
                SupportLastUpdaterEntity updateEntity = (SupportLastUpdaterEntity) entity;
                updateEntity.setLastUpdater(userInfo.getUserId());
            }
            if(mgrService.update(entity)) {
                setSuccessResult(resultResp);
                resultResp.setResult(entity.getId());
            }
        }
        return resultResp;
    }

    /**
     * 删除数据
     * @param idRequest ID请求对象
     * @param mgrService 服务类
     * @return 返回结果
     */
    protected RestBaseResponse delete(IdRequest idRequest, MgrService mgrService) {
        checkRequestVO(idRequest);
        RestBaseResponse response = new RestBaseResponse();
        if(mgrService.delete(idRequest.getId())) {
            setSuccessResult(response);
        }
        return response;
    }
}
