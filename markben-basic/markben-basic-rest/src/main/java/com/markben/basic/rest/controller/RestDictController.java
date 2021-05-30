package com.markben.basic.rest.controller;

import com.markben.basic.common.entity.TSysDict;
import com.markben.beans.response.BaseResponse;
import com.markben.beans.response.CollectionResponse;
import com.markben.beans.response.ResultResponse;
import com.markben.basic.common.service.DictService;
import com.markben.common.utils.CollectionUtils;
import com.markben.rest.common.controller.AbstractRestController;
import com.markben.basic.rest.helper.DictHelper;
import com.markben.rest.common.response.RestCollectionResponse;
import com.markben.rest.common.response.RestResultResponse;
import com.markben.rest.common.vo.IdRequest;
import com.markben.rest.common.vo.LabelValueVO;
import com.markben.basic.rest.vo.dict.CreateDictRequest;
import com.markben.basic.rest.vo.dict.DictDetailVO;
import com.markben.basic.rest.vo.dict.DictItemVO;
import com.markben.basic.rest.vo.dict.UpdateDictRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 数据字典控制器类
 * @author 乌草坡
 * @since 0.0.1
 */
@RestController
@RequestMapping("/rest/dict")
@Api(value = "数据字典接口", tags = {"数据字典接口"})
public class RestDictController extends AbstractRestController {

    private DictService dictService;

    public RestDictController(DictService dictService) {
        this.dictService = dictService;
    }

    /**
     * 创建数据字典
     * @param request 请求对象
     * @param createRequest 创建请求对象
     * @return 返回结果
     */
    @PostMapping(value = "/create", produces = PRODUCES_FORMAT)
    @ApiOperation(value = "创建数据字典", notes = "添加数据字典")
    public ResultResponse<String> create(HttpServletRequest request, @RequestBody CreateDictRequest createRequest) {
        return super.create(request, createRequest, dictService, () -> DictHelper.convert(createRequest));
    }

    @PostMapping(value = "/update", produces = PRODUCES_FORMAT)
    @ApiOperation(value = "更新数据字典", notes = "更新数据字典")
    public ResultResponse<String> update(HttpServletRequest request, @RequestBody UpdateDictRequest updateRequest) {
        return super.update(updateRequest, dictService, () -> DictHelper.convert(updateRequest));
    }

    /**
     * 根据ID删除数据字典项
     * @param idRequest 请求对象
     * @return 返回结果
     */
    @PostMapping(value = "/delete", produces = PRODUCES_FORMAT)
    @ApiOperation(value = "删除数据字典", notes = "删除数据字典")
    public BaseResponse delete(@RequestBody IdRequest idRequest) {
        return super.delete(idRequest, dictService);
    }

    /**
     * 获取所有数据字典列表
     * @return 返回结果
     */
    @GetMapping(value = "/list", produces = PRODUCES_FORMAT)
    @ApiOperation(value = "获取数据字典列表", notes = "获取数据字典列表")
    public CollectionResponse<DictItemVO> list() {
        CollectionResponse<DictItemVO> response = new RestCollectionResponse<>();
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
    @GetMapping(value = "/get/{id}", produces = PRODUCES_FORMAT)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name="id", value = "数据字典ID", required = true, dataType = "String")
    })
    @ApiOperation(value = "获取数据字典详细信息", notes = "获取数据字典详细信息")
    public ResultResponse<DictDetailVO> get(@PathVariable String id) {
        ResultResponse<DictDetailVO> response = new RestResultResponse<>();
        Optional<TSysDict> dictOpt = dictService.find(id);
        dictOpt.ifPresent(dict -> {
            DictDetailVO detailVO = new DictDetailVO(dict.getId(), dict.getParentId(), dict.getName(),
                    dict.getSortOrder(), dict.getValue(), dict.getState(), dict.getCreator());
            detailVO.setCreateTime(dict.getCreateTime());
            response.setResult(detailVO);
            super.setSuccessResult(response);
        });
        return response;
    }

    /**
     * 获取数据字典项列表通过数字字典ID
     * @param id 数据字典ID
     * @return 返回结果
     */
    @GetMapping(value = "/items/id/{id}", produces = PRODUCES_FORMAT)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name="id", value = "数据字典ID", required = true, dataType = "String")
    })
    @ApiOperation(value = "获取数据字典列表", notes = "获取数据字典列表")
    public CollectionResponse<LabelValueVO> itemsById(@PathVariable String id) {
        CollectionResponse<LabelValueVO> response = new RestCollectionResponse<>();
        List<TSysDict> items = dictService.getSubItemById(id);
        if(CollectionUtils.isNotEmpty(items)) {
            response.setData(convert(items));
            super.setSuccessResult(response);
        }
        return response;
    }

    /**
     * 获取数据字典项列表通过业务值
     * @param value 数据字典业务值
     * @return 返回结果
     */
    @GetMapping(value = "/item/{value}", produces = PRODUCES_FORMAT)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name="value", value = "数据字典业务值", required = true, dataType = "String")
    })
    @ApiOperation(value = "获取数据字典列表", notes = "获取数据字典列表")
    public CollectionResponse<LabelValueVO> item(@PathVariable String value) {
        CollectionResponse<LabelValueVO> response = new RestCollectionResponse<>();
        List<TSysDict> items = dictService.getItemByValue(value);
        if(CollectionUtils.isNotEmpty(items)) {
            response.setData(convert(items));
            super.setSuccessResult(response);
        }
        return response;
    }

    /**
     * 转换列表
     * @param list
     * @return
     */
    private List<LabelValueVO> convert(Collection<TSysDict> list) {
        return list.stream().map(d -> new LabelValueVO(d.getName(), d.getValue())).collect(Collectors.toList());
    }
}
