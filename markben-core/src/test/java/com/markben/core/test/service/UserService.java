package com.markben.core.test.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.markben.common.enums.YesNoType;
import com.markben.core.service.MgrServiceImpl;
import com.markben.core.test.dao.UserMapper;
import com.markben.core.test.entity.TTestUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @autor 乌草坡 2020-02-28
 * @since 1.0
 */
@Service
public class UserService extends MgrServiceImpl<UserMapper, TTestUser> {

    public Optional<TTestUser> getUserOfLogin(String username) {

        QueryWrapper<TTestUser> queryWrapper = new QueryWrapper();
        /*
        QueryChainWrapper queryWrapper = super.query().eq("state", YesNoType.YES.getIndex())
                .and(w -> w.eq("", username).or().eq("mobile", username));*/
        queryWrapper.select().eq("state", YesNoType.YES.getIndex())
                .and(w -> w.eq("username", username).or().eq("mobile", username));
        TTestUser user = getOne(queryWrapper, false);
        return Optional.ofNullable(user);
    }

}
