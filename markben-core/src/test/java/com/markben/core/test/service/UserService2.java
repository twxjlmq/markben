package com.markben.core.test.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markben.common.enums.YesNoType;
import com.markben.core.test.dao.UserMapper;
import com.markben.core.test.entity.TTestUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @autor 乌草坡 2020-02-28
 * @since 1.0
 */
@Service
public class UserService2 extends ServiceImpl<UserMapper, TTestUser> {

    public Optional<TTestUser> getUserOfLogin(String username) {

        /*QueryChainWrapper queryWrapper = super.query().eq("state", YesNoType.YES.getIndex())
                .and(w -> w.eq("username", username).or().eq("mobile", username));*/
        /*QueryChainWrapper queryWrapper = super.query().eq("state", YesNoType.YES.getIndex())
                .and(w -> w.eq("username", username).or().eq("mobile", username));*/
        QueryWrapper<TTestUser> queryWrapper = new QueryWrapper();
        queryWrapper.eq("state", YesNoType.YES.getIndex());
        queryWrapper.and(w -> w.eq("username", username).or().eq("mobile", username));

        TTestUser user = getOne(queryWrapper, false);
        return Optional.ofNullable(user);
    }

}
