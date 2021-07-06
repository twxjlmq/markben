package com.markben.basic.common.service;

import com.markben.basic.common.bean.MenuItem;
import com.markben.basic.common.entity.TSysMenu;
import com.markben.beans.bean.StateSearch;
import com.markben.core.service.MgrService;

import java.util.Collection;
import java.util.List;

/**
 * @author 乌草坡
 * @since 0.0.1
 */
public interface MenuService extends MgrService<TSysMenu> {

    /**
     * 获取管理列表
     * @param search 搜索对象
     * @return 返回管理列表
     */
    List<MenuItem> getMgrList(StateSearch search);

}
