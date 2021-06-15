package com.markben.org.common.service.impl;

import com.markben.org.common.dao.DeptHeadDao;
import com.markben.org.common.entity.TSysDeptHead;
import com.markben.org.common.service.DeptHeadService;
import com.markben.core.service.MgrServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 乌草坡
 * @since 0.0.1
 */
@Service
public class DeptHeadServiceImpl extends MgrServiceImpl<DeptHeadDao, TSysDeptHead> implements DeptHeadService {
}
