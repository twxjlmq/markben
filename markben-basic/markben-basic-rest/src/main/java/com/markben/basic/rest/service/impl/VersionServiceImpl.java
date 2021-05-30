package com.markben.basic.rest.service.impl;

import com.markben.basic.rest.entity.TSysVersion;
import com.markben.basic.rest.service.VersionService;
import com.markben.core.service.EnhanceServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 系统版本服务实现类
 * @author 乌草坡
 * @since 0.0.1
 */
@Service
public class VersionServiceImpl extends EnhanceServiceImpl<TSysVersion> implements VersionService {
}
