package com.markben.basic.common.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.markben.basic.common.entity.TSysUser;
import com.markben.basic.common.service.UserService;
import com.markben.common.enums.YesNoType;
import com.markben.common.security.SecurityUtils;
import com.markben.common.utils.CollectionUtils;
import com.markben.common.utils.StringUtils;
import com.markben.core.service.MgrServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 用户服务实现类
 * @author 乌草坡
 * @since 0.0.1
 */
@Service
public class UserServiceImpl extends MgrServiceImpl<TSysUser> implements UserService {

    @Override
    @Transactional
    public boolean save(TSysUser entity) {
        if(StringUtils.isNotEmpty(entity.getPassword())) {
            Optional<String> opt = getMd5SaltPassword(entity.getPassword());
            opt.ifPresent(entity::setPassword);
        }
        return super.save(entity);
    }

    @Override
    public Optional<String> getMd5SaltPassword(String password) {
        if(StringUtils.isEmpty(password)) {
            return Optional.empty();
        }
        return Optional.ofNullable(SecurityUtils.encryptPassword(password));
    }

    @Override
    public void changePassword(String userId, String newPassword, String oldPassword) {

    }

    @Override
    public Optional<TSysUser> getUser(String username) {
        List<TSysUser> userList = finds("username", username);
        if(CollectionUtils.isNotEmpty(userList)) {
            return Optional.of(userList.get(0));
        }
        return Optional.empty();
    }

    @Override
    public Optional<TSysUser> getUserOfLogin(String username) {
        QueryChainWrapper queryWrapper = super.query().eq("state", YesNoType.YES.getIndex())
                .and(w -> w.eq("username", username).or().eq("mobile", username));
        TSysUser user = getOne(queryWrapper, false);
        return Optional.ofNullable(user);
    }
}
