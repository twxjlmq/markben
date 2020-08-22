package com.markben.basic.common.service.impl;

import com.markben.basic.common.entity.TSysUser;
import com.markben.basic.common.service.IUserService;
import com.markben.beans.bean.IUserInfo;
import com.markben.beans.response.IResultResponse;
import com.markben.common.constant.MarkbenConstant;
import com.markben.common.security.SecurityUtils;
import com.markben.common.utils.CollectionUtils;
import com.markben.common.utils.StringUtils;
import com.markben.core.service.EnhanceServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 乌草坡
 * @since 1.0
 */
@Service
public class UserServiceImpl extends EnhanceServiceImpl<TSysUser> implements IUserService {

    @Override
    @Transactional
    public boolean save(TSysUser entity) {
        if(StringUtils.isNotEmpty(entity.getPassword())) {
            entity.setPassword(getMd5SaltPassword(entity.getPassword()));
        }
        return super.save(entity);
    }

    @Override
    public String getMd5SaltPassword(String password) {
        if(StringUtils.isEmpty(password)) {
            return null;
        }
        return SecurityUtils.encryptPassword(password);
    }

    @Override
    public void changePwd(String userId, String newPassword, String oldPassword) {

    }

    @Override
    public TSysUser getUser(String username) {
        List<TSysUser> userList = finds("username", username);
        if(CollectionUtils.isNotEmpty(userList)) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public TSysUser getUserOfLogin(String username) {
        return getOne(super.query().eq("username", username).or().eq("mobile", username), false);
    }
}
