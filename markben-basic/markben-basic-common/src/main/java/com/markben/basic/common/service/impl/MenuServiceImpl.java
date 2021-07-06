package com.markben.basic.common.service.impl;

import com.markben.basic.common.bean.MenuItem;
import com.markben.basic.common.dao.MenuDao;
import com.markben.basic.common.entity.TSysMenu;
import com.markben.basic.common.service.MenuService;
import com.markben.beans.bean.StateSearch;
import com.markben.core.service.MgrServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 乌草坡
 * @since 0.0.1
 */
@Service
public class MenuServiceImpl extends MgrServiceImpl<MenuDao, TSysMenu> implements MenuService {

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<MenuItem> getMgrList(StateSearch search) {
        return getBaseMapper().queryMgrList(search);
    }
}
