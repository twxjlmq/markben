package com.markben.org.common.service.impl;

import com.markben.core.service.MgrServiceImpl;
import com.markben.org.common.dao.PositionDao;
import com.markben.org.common.dao.PositionUserDao;
import com.markben.org.common.entity.TSysPositionUser;
import com.markben.org.common.service.PositionUserService;
import org.springframework.stereotype.Service;

/**
 * @author 乌草坡
 * @since 0.0.1
 */
@Service
public class PositionUserServiceImpl extends MgrServiceImpl<PositionUserDao, TSysPositionUser> implements PositionUserService {
}
