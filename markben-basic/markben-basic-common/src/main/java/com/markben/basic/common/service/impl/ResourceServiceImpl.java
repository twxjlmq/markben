package com.markben.basic.common.service.impl;

import com.markben.basic.common.dao.ResourceDao;
import com.markben.basic.common.entity.TSysResource;
import com.markben.basic.common.service.ResourceService;
import com.markben.core.service.MgrServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 系统资源服务类
 * @author 乌草坡
 * @since 0.0.1
 */
@Service
public class ResourceServiceImpl extends MgrServiceImpl<ResourceDao, TSysResource> implements ResourceService {

}
