package com.markben.core.test;

import com.markben.common.logger.ILogger;
import com.markben.common.utils.LoggerUtils;
import com.markben.core.callback.IUpdateCallbackAware;
import com.markben.core.entity.TUser;
import com.markben.core.mapper.UserMapper;
import org.springframework.stereotype.Component;

/**
 * @autor 乌草坡 2020-03-02
 * @since 1.0
 */
@Component
public class UserUpdateCallback implements IUpdateCallbackAware<UserMapper, TUser> {

    private static final ILogger logger = LoggerUtils.getLogger(UserUpdateCallback.class);

    @Override
    public void callback(UserMapper target, TUser value) {
        LoggerUtils.debug(logger, "正在更新[{}]的用户信息.", value.getId());
    }
}