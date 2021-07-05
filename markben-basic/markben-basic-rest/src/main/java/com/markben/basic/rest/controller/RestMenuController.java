package com.markben.basic.rest.controller;

import com.markben.basic.common.entity.TSysMenu;
import com.markben.basic.common.service.MenuService;
import com.markben.basic.rest.vo.menu.CreateMenuRequest;
import com.markben.basic.rest.vo.menu.UpdateMenuRequest;
import com.markben.common.utils.ObjectUtils;
import com.markben.rest.common.controller.AbstractRestController;
import com.markben.rest.common.response.RestResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 菜单控制器
 * @author 乌草坡
 * @since 0.0.1
 */
@RestController
@RequestMapping("/rest/menu")
@Api(tags = "菜单接口")
public class RestMenuController extends AbstractRestController {

    private MenuService menuService;

    public RestMenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 创建菜单
     * @param request Http请求对象
     * @param createRequest 创建请求对象
     * @return 返回创建结果
     */
    @PostMapping(value = "/create", produces = PRODUCES_FORMAT)
    @ApiOperation(value = "创建菜单接口", notes = "创建菜单接口")
    public RestResultResponse<String> create(HttpServletRequest request, @RequestBody CreateMenuRequest createRequest) {
        return super.create(request, createRequest, menuService, () -> {
            TSysMenu menu = ObjectUtils.convertObject(TSysMenu.class, createRequest).get();
            return menu;
        });
    }

    /**
     * 更新菜单
     * @param request Http请求对象
     * @param updateRequest 更新请求对象
     * @return 返回更新结果
     */
    @PostMapping(value = "/update", produces = PRODUCES_FORMAT)
    @ApiOperation(value = "更新菜单接口", notes = "更新菜单接口")
    public RestResultResponse<String> update(HttpServletRequest request, @RequestBody UpdateMenuRequest updateRequest) {
        return super.update(request, updateRequest, menuService, () -> {
            TSysMenu menu = ObjectUtils.convertObject(TSysMenu.class, updateRequest).get();
            return menu;
        });
    }
}
