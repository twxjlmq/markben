package com.markben.basic.rest.controller;

import com.markben.beans.response.BaseResponse;
import com.markben.beans.response.ResultResponse;
import com.markben.core.bean.PKStringEntity;
import com.markben.core.bean.SupportCreatorEntity;
import com.markben.core.bean.SupportUpdateTime;
import com.markben.core.service.MgrService;
import com.markben.multi.tenancy.entity.SupportTenantEntity;
import com.markben.rest.common.response.RestBaseResponse;
import com.markben.rest.common.response.RestResultResponse;
import com.markben.rest.common.vo.AbstractRestRequest;
import com.markben.rest.common.vo.IdRequest;
import com.markben.rest.org.bean.OrgUserInfo;
import com.markben.rest.org.controller.AbstractOrgRestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.function.Supplier;

/**
 * 基础Rest抽象控制器类
 * @author 乌草坡
 * @since 0.0.1
 */
public abstract class AbstractBasicRestController extends AbstractOrgRestController {

    /**
     * 创建数据
     * @param request 请求对象
     * @param createRequest 创建请求对象
     * @param mgrService 服务类
     * @param supplier 提供实体对象函数试方法
     * @return 返回结果
     */
    protected ResultResponse<String> create(HttpServletRequest request, AbstractRestRequest createRequest,
                                            MgrService mgrService, Supplier<PKStringEntity> supplier) {
        super.checkRequestVO(createRequest);
        ResultResponse<String> resultResp = new RestResultResponse<>();
        OrgUserInfo userInfo = getUserInfoByRequest(request);
        PKStringEntity entity = supplier.get();
        if(null != entity) {
            if(entity instanceof SupportCreatorEntity) {
                SupportCreatorEntity creatorEntity = (SupportCreatorEntity) entity;
                creatorEntity.setCreator(userInfo.getTenantUserId());
            }
            if(entity instanceof SupportTenantEntity) {
                SupportTenantEntity tenantEntity = (SupportTenantEntity) entity;
                tenantEntity.setTenantId(userInfo.getTenantId());
            }
            if(mgrService.save(entity)) {
                super.setSuccessResult(resultResp);
                resultResp.setResult(entity.getId());
            }
        }
        return resultResp;
    }

    /**
     * 更新数据
     * @param updateRequest 更新请求对象
     * @param mgrService 服务类
     * @param supplier 提供实体对象函数试方法
     * @return 返回结果
     */
    protected ResultResponse<String> update(AbstractRestRequest updateRequest,
                                            MgrService mgrService, Supplier<PKStringEntity> supplier) {
        super.checkRequestVO(updateRequest);
        ResultResponse<String> resultResp = new RestResultResponse<>();
        PKStringEntity entity = supplier.get();
        if(null != entity) {
            if(mgrService.update(entity)) {
                super.setSuccessResult(resultResp);
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
    protected BaseResponse delete(IdRequest idRequest, MgrService mgrService) {
        super.checkRequestVO(idRequest);
        BaseResponse response = new RestBaseResponse();
        if(mgrService.delete(idRequest.getId())) {
            super.setSuccessResult(response);
        }
        return response;
    }

}
