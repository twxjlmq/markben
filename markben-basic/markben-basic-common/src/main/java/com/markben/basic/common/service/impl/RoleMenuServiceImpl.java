package com.markben.basic.common.service.impl;

import com.markben.basic.common.dao.RoleMenuDao;
import com.markben.basic.common.entity.TSysRoleMenu;
import com.markben.basic.common.service.RoleMenuService;
import com.markben.core.service.MgrServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 乌草坡
 * @since 0.0.1
 */
@Service
public class RoleMenuServiceImpl extends MgrServiceImpl<RoleMenuDao, TSysRoleMenu> implements RoleMenuService {
}
