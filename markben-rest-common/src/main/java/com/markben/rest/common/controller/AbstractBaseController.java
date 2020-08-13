package com.markben.rest.common.controller;

import com.markben.common.logger.ILogger;
import com.markben.common.utils.LoggerUtils;

/**
 * 抽象基础控制器类
 * @author 乌草坡
 * @since 1.0
 */
public abstract class AbstractBaseController {

    private ILogger logger;

    public AbstractBaseController() {
        logger = LoggerUtils.getLogger(getClass());
    }

    public ILogger getLogger() {
        return logger;
    }
}
