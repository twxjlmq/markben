package com.markben.basic.common.dao;

import com.markben.basic.common.bean.MenuItem;
import com.markben.basic.common.entity.TSysMenu;
import com.markben.beans.bean.StateSearch;
import com.markben.core.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * @author 乌草坡
 * @since 0.0.1
 */
@Mapper
public interface MenuDao extends BaseDao<TSysMenu> {

    /**
     * 查询管理列表
     * @param search 搜索对象
     * @return 返回菜单项列表
     */
    List<MenuItem> queryMgrList(StateSearch search);

}
