package com.markben.basic.rest.controller;

import com.markben.basic.common.entity.TSysDict;
import com.markben.basic.common.service.ISysDictService;
import com.markben.basic.rest.helper.DictHelper;
import com.markben.beans.bean.IUserInfo;
import com.markben.beans.enums.MarkbenStatusEnums;
import com.markben.rest.common.controller.AbstractApiController;
import com.markben.rest.common.response.RestResultResponse;
import com.markben.basic.rest.vo.dict.CreateDictRequest;
import com.markben.beans.response.IResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
            resultResp.setStatus(MarkbenStatusEnums.SUCCESS.getStatus());
            resultResp.setMsg(MarkbenStatusEnums.SUCCESS.getMsg());
            resultResp.setResult(dict.getId());
        }
        return resultResp;
    }

}
