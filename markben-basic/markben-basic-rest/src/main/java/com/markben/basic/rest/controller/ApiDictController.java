package com.markben.basic.rest.controller;

import com.markben.basic.common.entity.TSysDict;
import com.markben.basic.common.service.ISysDictService;
import com.markben.basic.rest.helper.DictHelper;
import com.markben.basic.rest.vo.dict.CreateDictRequest;
import com.markben.basic.rest.vo.dict.DictItemVO;
import com.markben.basic.rest.vo.dict.UpdateDictRequest;
import com.markben.beans.bean.IUserInfo;
import com.markben.beans.response.IBaseResponse;
import com.markben.beans.response.ICollectionResponse;
import com.markben.beans.response.IResultResponse;
import com.markben.common.utils.CollectionUtils;
import com.markben.rest.common.controller.AbstractApiController;
import com.markben.rest.common.response.RestBaseResponse;
import com.markben.rest.common.response.RestCollectionResponse;
import com.markben.rest.common.response.RestResultResponse;
import com.markben.rest.common.vo.IdRequest;
import com.markben.rest.common.vo.LabelValueVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据字典接口
 * @author 乌草坡
 * @since 1.0
 */
@RestController
@RequestMapping("/api/dict")
@Api(value = "数据字典接口", tags = {"数据字典接口"})
public class ApiDictController extends AbstractApiController {

    @Autowired
    private ISysDictService dictService;

    /**
     * 创建数据字典
     * @param request 请求对象
     * @param createRequest 创建请求对象
     * @return 返回结果
     */
    @PostMapping(value = "/create", produces = PRODUCES_JSON)
    @ApiOperation(value = "创建数据字典", notes = "添加数据字典")
    public IResultResponse<String> create(HttpServletRequest request, @RequestBody CreateDictRequest createRequest) {
        super.checkRequestVO(createRequest);
        IResultResponse<String> resultResp = new RestResultResponse<>();
        IUserInfo userInfo = getUserInfoByRequest(request);
        TSysDict dict = DictHelper.convert(createRequest);
        dict.setCorpUserId(userInfo.getCorpUserId());
        dict.setCorpId(userInfo.getCorpId());
        if(dictService.save(dict)) {
            super.setSuccessResult(resultResp);
            resultResp.setResult(dict.getId());
        }
        return resultResp;
    }

    @PostMapping(value = "/update", produces = PRODUCES_JSON)
    @ApiOperation(value = "更新数据字典", notes = "更新数据字典")
    public IResultResponse<String> update(HttpServletRequest request, @RequestBody UpdateDictRequest updateRequest) {
        super.checkRequestVO(updateRequest);
        IResultResponse<String> resultResp = new RestResultResponse<>();
        TSysDict dict = DictHelper.convert(updateRequest);
        if(dictService.update(dict)) {
            super.setSuccessResult(resultResp);
            resultResp.setResult(dict.getId());
        }
        return resultResp;
    }

    /**
     * 根据ID删除数据字典项
     * @param idRequest 请求对象
     * @return 返回结果
     */
    @PostMapping(value = "/delete", produces = PRODUCES_JSON)
    @ApiOperation(value = "删除数据字典", notes = "删除数据字典")
    public IBaseResponse delete(@RequestBody IdRequest idRequest) {
        super.checkRequestVO(idRequest);
        IBaseResponse response = new RestBaseResponse();
        if(dictService.delete(idRequest.getId())) {
            super.setSuccessResult(response);
        }
        return response;
    }

    /**
     * 获取所有数据字典列表
     * @return 返回结果
     */
    @GetMapping(value = "/list", produces = PRODUCES_JSON)
    @ApiOperation(value = "获取数据字典列表", notes = "获取数据字典列表")
    public ICollectionResponse<DictItemVO> list() {
        ICollectionResponse<DictItemVO> response = new RestCollectionResponse<>();
        List<TSysDict> dictList = dictService.findAll();
        if(CollectionUtils.isNotEmpty(dictList)) {
           List<DictItemVO> list = dictList.stream().map(d -> {
                DictItemVO dictItemVO = new DictItemVO(d.getId(), d.getParentId(), d.getName(), d.getSortOrder(),
                        d.getValue(), d.getState());
                dictItemVO.setCreateTime(d.getCreateTime());
                return dictItemVO;
            }).collect(Collectors.toList());
            super.setSuccessResult(response);
           response.setData(list);
        }
        return response;
    }

    /**
     * 获取数据字典详情信息
     * @return 返回结果
     */
    @GetMapping(value = "/get/{id}", produces = PRODUCES_JSON)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name="id", value = "数据字典ID", required = true, dataType = "String")
    })
    @ApiOperation(value = "获取数据字典列表", notes = "获取数据字典列表")
    public IResultResponse<DictItemVO> get(@PathVariable String id) {
        return null;
    }

    /**
     * 获取数据字典项列表通过数字字典ID
     * @param id 数据字典ID
     * @return 返回结果
     */
    @GetMapping(value = "/items/id/{id}", produces = PRODUCES_JSON)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name="id", value = "数据字典ID", required = true, dataType = "String")
    })
    @ApiOperation(value = "获取数据字典列表", notes = "获取数据字典列表")
    public ICollectionResponse<LabelValueVO> itemsById(@PathVariable String id) {
        return null;
    }

    /**
     * 获取数据字典项列表通过业务值
     * @param value 数据字典业务值
     * @return 返回结果
     */
    @GetMapping(value = "/item/{value}", produces = PRODUCES_JSON)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name="value", value = "数据字典业务值", required = true, dataType = "String")
    })
    @ApiOperation(value = "获取数据字典列表", notes = "获取数据字典列表")
    public ICollectionResponse<LabelValueVO> item(@PathVariable String value) {
        return null;
    }

}
