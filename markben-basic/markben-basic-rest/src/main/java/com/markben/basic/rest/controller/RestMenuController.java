package com.markben.basic.rest.controller;

import com.markben.basic.common.bean.MenuItem;
import com.markben.basic.common.entity.TSysMenu;
import com.markben.basic.common.service.MenuService;
import com.markben.basic.rest.vo.menu.CreateMenuRequest;
import com.markben.basic.rest.vo.menu.MenuVO;
import com.markben.basic.rest.vo.menu.UpdateMenuRequest;
import com.markben.common.utils.CollectionUtils;
import com.markben.common.utils.ObjectUtils;
import com.markben.rest.common.controller.AbstractRestController;
import com.markben.rest.common.response.RestCollectionResponse;
import com.markben.rest.common.response.RestResultResponse;
import com.markben.rest.common.vo.StateSearchVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

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
     * 获取管理列表
     * @param search 搜索对象
     * @return 返回列表结果
     */
    @GetMapping(value = "/list", produces = PRODUCES_FORMAT)
    @ApiOperation(value = "获取菜单列表", notes = "获取菜单列表接口")
    public RestCollectionResponse<MenuVO> getList(StateSearchVO search) {
        RestCollectionResponse<MenuVO> restResp = new RestCollectionResponse<>();
        List<MenuItem> menus = menuService.getMgrList(search);
        if(CollectionUtils.isNotEmpty(menus)) {
            List<MenuVO> menuList = menus.stream().map(m -> {
                MenuVO menuVO = ObjectUtils.convertObject(MenuVO.class, m).get();
                return menuVO;
            }).collect(Collectors.toList());
            restResp.setData(menuList);
            super.setSuccessResult(restResp);
        }
        return restResp;
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
