package com.markben.basic.api.controller;

import com.markben.api.common.controller.AbstractApiController;
import com.markben.api.common.response.RestResultResponse;
import com.markben.basic.api.vo.dict.CreateDictRequest;
import com.markben.beans.response.IResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @PostMapping(value = "/create", produces = PRODUCES_JSON)
    @ApiOperation(value = "创建数据字典", notes = "添加数据字典")
    public IResultResponse<String> create(HttpServletRequest request, @RequestBody CreateDictRequest createRequest) {
        IResultResponse<String> resultResp = new RestResultResponse<>();
        return resultResp;
    }

}
