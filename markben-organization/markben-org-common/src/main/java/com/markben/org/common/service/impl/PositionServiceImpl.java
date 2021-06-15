package com.markben.org.common.service.impl;

import com.markben.org.common.dao.PositionDao;
import com.markben.org.common.entity.TSysPosition;
import com.markben.org.common.service.PositionService;
import com.markben.core.service.MgrServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 乌草坡
 * @since 0.0.1
 */
@Service
public class PositionServiceImpl extends MgrServiceImpl<PositionDao, TSysPosition> implements PositionService {
}
