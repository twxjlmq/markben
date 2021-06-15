package com.markben.basic.common.service.impl;

import com.markben.basic.common.dao.RoleDao;
import com.markben.basic.common.entity.TSysRole;
import com.markben.basic.common.service.RoleService;
import com.markben.core.service.MgrServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 乌草坡
 * @since 0.0.1
 */
@Service
public class RoleServiceImpl extends MgrServiceImpl<RoleDao, TSysRole> implements RoleService {

}
