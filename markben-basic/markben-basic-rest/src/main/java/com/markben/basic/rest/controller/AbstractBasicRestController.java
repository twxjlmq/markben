package com.markben.basic.rest.controller;

import com.markben.beans.bean.IUserInfo;
import com.markben.beans.response.IBaseResponse;
import com.markben.beans.response.IResultResponse;
import com.markben.core.bean.ICreatorEntity;
import com.markben.core.service.IMgrService;
import com.markben.rest.common.controller.AbstractRestController;
import com.markben.rest.common.response.RestBaseResponse;
import com.markben.rest.common.response.RestResultResponse;
import com.markben.rest.common.vo.AbstractRestRequest;
import com.markben.rest.common.vo.IdRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Supplier;

/**
 * 基础Rest抽象控制器类
 * @author 乌草坡
 * @since 1.0
 */
public abstract class AbstractBasicRestController extends AbstractRestController {

    /**
     * 创建数据
     * @param request 请求对象
     * @param createRequest 创建请求对象
     * @param mgrService 服务类
     * @param supplier 提供实体对象函数试方法
     * @return 返回结果
     */
    protected IResultResponse<String> create(HttpServletRequest request, AbstractRestRequest createRequest,
                                             IMgrService mgrService, Supplier<ICreatorEntity> supplier) {
        super.checkRequestVO(createRequest);
        IResultResponse<String> resultResp = new RestResultResponse<>();
        IUserInfo userInfo = getUserInfoByRequest(request);
        ICreatorEntity entity = supplier.get();
        if(null != entity) {
            entity.setCorpUserId(userInfo.getCorpUserId());
            entity.setCorpId(userInfo.getCorpId());
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
    protected IResultResponse<String> update(AbstractRestRequest updateRequest,
                                             IMgrService mgrService, Supplier<ICreatorEntity> supplier) {
        super.checkRequestVO(updateRequest);
        IResultResponse<String> resultResp = new RestResultResponse<>();
        ICreatorEntity entity = supplier.get();
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
    protected IBaseResponse delete(IdRequest idRequest, IMgrService mgrService) {
        super.checkRequestVO(idRequest);
        IBaseResponse response = new RestBaseResponse();
        if(mgrService.delete(idRequest.getId())) {
            super.setSuccessResult(response);
        }
        return response;
    }

}
