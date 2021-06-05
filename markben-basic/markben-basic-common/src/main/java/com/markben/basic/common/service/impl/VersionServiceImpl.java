package com.markben.basic.common.service.impl;

import com.markben.basic.common.entity.TSysVersion;
import com.markben.basic.common.service.VersionService;
import com.markben.core.service.MgrServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 系统版本服务实现类
 * @author 乌草坡
 * @since 0.0.1
 */
@Service
public class VersionServiceImpl extends MgrServiceImpl<TSysVersion> implements VersionService {
}
