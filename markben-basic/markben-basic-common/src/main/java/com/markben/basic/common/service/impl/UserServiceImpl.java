package com.markben.basic.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.markben.basic.common.dao.UserDao;
import com.markben.basic.common.entity.TSysUser;
import com.markben.basic.common.service.UserService;
import com.markben.beans.enums.MarkbenStatusEnums;
import com.markben.beans.exception.MarkbenStatusException;
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
public class UserServiceImpl extends MgrServiceImpl<UserDao, TSysUser> implements UserService {

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
    @Transactional
    public void changePassword(String userId, String newPassword, String oldPassword) {
        StringUtils.isAssert("userId", "userId参数不能为空");
        StringUtils.isAssert("newPassword", "newPassword参数不能为空");
        StringUtils.isAssert("oldPassword", "oldPassword参数不能为空");
        TSysUser user = getById(userId);
        if(null == user) {
            throw new MarkbenStatusException(MarkbenStatusEnums.USER_NOT_EXIST.getStatus(), "用户ID为["+userId+"]的用户不存在");
        }
        String oldPasswordMd5 = SecurityUtils.encryptPassword(oldPassword);
        if(!oldPasswordMd5.equals(user.getPassword())) {
            throw new MarkbenStatusException(MarkbenStatusEnums.CHANGE_PASSWORD_ERROR.getStatus(), "旧密码错误");
        }
        String newPasswordMd5 = SecurityUtils.encryptPassword(newPassword);
        user.setPassword(newPasswordMd5);
        super.update(user);
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
        QueryWrapper<TSysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.select().eq("state", YesNoType.YES.getIndex())
                .and(w -> w.eq("username", username).or().eq("mobile", username));
        TSysUser user = getOne(queryWrapper, false);
        return Optional.ofNullable(user);
    }

    @Override
    public boolean isExistUsername(String username) {
        if(StringUtils.isEmpty(username)) {
            return false;
        }
        QueryWrapper<TSysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TSysUser::getUsername, username);
        return super.getBaseMapper().selectCount(queryWrapper) > 0;
    }

    @Override
    public boolean isExistMobile(String mobile) {
        if(StringUtils.isEmpty(mobile)) {
            return false;
        }
        QueryWrapper<TSysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TSysUser::getMobile, mobile);
        return super.getBaseMapper().selectCount(queryWrapper) > 0;
    }
}
