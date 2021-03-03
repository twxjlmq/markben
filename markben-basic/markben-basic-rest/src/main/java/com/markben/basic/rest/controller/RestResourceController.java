package com.markben.basic.rest.controller;

import com.markben.basic.common.entity.TSysResource;
import com.markben.basic.common.service.IResourceService;
import com.markben.basic.rest.helper.DictHelper;
import com.markben.basic.rest.vo.dict.DictDetailVO;
import com.markben.basic.rest.vo.dict.DictItemVO;
import com.markben.basic.rest.vo.resource.CreateResourceRequest;
import com.markben.basic.rest.vo.resource.UpdateResourceRequest;
import com.markben.beans.bean.IUserInfo;
import com.markben.beans.response.IBaseResponse;
import com.markben.beans.response.ICollectionResponse;
import com.markben.beans.response.IResultResponse;
import com.markben.rest.common.controller.AbstractRestController;
import com.markben.rest.common.response.RestBaseResponse;
import com.markben.rest.common.response.RestCollectionResponse;
import com.markben.rest.common.response.RestResultResponse;
import com.markben.rest.common.vo.IdRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * REST资源控制器类
 * @author 乌草坡
 * @since 1.0
 */
@RestController
@RequestMapping("/rest/resource")
@Api(value = "数据字典接口", tags = {"数据字典接口"})
public class RestResourceController extends AbstractBasicRestController {

    private IResourceService resourceService;

    public RestResourceController(IResourceService resourceService) {
        this.resourceService = resourceService;
    }

    /**
     * 创建资源
     * @param request 请求对象
     * @param createRequest 创建请求对象
     * @return 返回结果
     */
    @PostMapping(value = "/create", produces = PRODUCES_FORMAT)
    @ApiOperation(value = "创建资源", notes = "添加资源")
    public IResultResponse<String> create(HttpServletRequest request, @RequestBody CreateResourceRequest createRequest) {
        return super.create(request, createRequest, resourceService, TSysResource::new);
    }

    /**
     * 更新资源
     * @param request 请求对象
     * @param updateRequest 更新请求对象
     * @return 返回结果
     */
    @PostMapping(value = "/update", produces = PRODUCES_FORMAT)
    @ApiOperation(value = "更新资源", notes = "更新资源")
    public IResultResponse<String> update(HttpServletRequest request, @RequestBody UpdateResourceRequest updateRequest) {
        super.checkRequestVO(updateRequest);
        IResultResponse<String> resultResp = new RestResultResponse<>();
        TSysResource resource = new TSysResource();
        if(resourceService.update(resource)) {
            super.setSuccessResult(resultResp);
            resultResp.setResult(resource.getId());
        }
        return resultResp;
    }

    /**
     * 根据ID删除资源
     * @param idRequest 请求对象
     * @return 返回结果
     */
    @PostMapping(value = "/delete", produces = PRODUCES_FORMAT)
    @ApiOperation(value = "删除资源", notes = "删除资源")
    public IBaseResponse delete(@RequestBody IdRequest idRequest) {
        super.checkRequestVO(idRequest);
        IBaseResponse response = new RestBaseResponse();
        if(resourceService.delete(idRequest.getId())) {
            super.setSuccessResult(response);
        }
        return response;
    }

    /**
     * 获取所有资源列表
     * @return 返回结果
     */
    @GetMapping(value = "/list", produces = PRODUCES_FORMAT)
    @ApiOperation(value = "获取资源列表", notes = "获取资源列表")
    public ICollectionResponse<DictItemVO> list() {
        ICollectionResponse<DictItemVO> response = new RestCollectionResponse<>();

        return response;
    }

    /**
     * 获取数据字典详情信息
     * @return 返回结果
     */
    @GetMapping(value = "/get/{id}", produces = PRODUCES_FORMAT)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name="id", value = "资源ID", required = true, dataType = "String")
    })
    @ApiOperation(value = "获取资源详细信息", notes = "获取资源详细信息")
    public IResultResponse<DictDetailVO> get(@PathVariable String id) {
        IResultResponse<DictDetailVO> response = new RestResultResponse<>();

        return response;
    }

}
