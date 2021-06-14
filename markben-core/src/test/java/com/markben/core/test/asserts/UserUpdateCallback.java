package com.markben.core.test.asserts;

import com.markben.common.logger.Logger;
import com.markben.common.utils.LoggerUtils;
import com.markben.core.callback.UpdateCallbackAware;
import com.markben.core.test.entity.TTestUser;
import com.markben.core.test.dao.UserMapper;
import org.springframework.stereotype.Component;

/**
 * @autor 乌草坡 2020-03-02
 * @since 1.0
 */
public class UserUpdateCallback implements UpdateCallbackAware<UserMapper, TTestUser> {

    private static final Logger logger = LoggerUtils.getLogger(UserUpdateCallback.class);

    @Override
    public void callback(UserMapper target, TTestUser value) {
        LoggerUtils.debug(logger, "正在更新[{}]的用户信息.", value.getId());
    }
}
