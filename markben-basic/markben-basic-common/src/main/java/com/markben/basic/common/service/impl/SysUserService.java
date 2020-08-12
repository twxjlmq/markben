package com.markben.basic.common.service.impl;

import com.markben.basic.common.entity.TSysUser;
import com.markben.basic.common.service.ISysUserService;
import com.markben.beans.bean.IUserInfo;
import com.markben.beans.response.IResultResponse;
import com.markben.common.constant.MarkbenConstant;
import com.markben.common.security.SecurityUtils;
import com.markben.common.utils.StringUtils;
import com.markben.core.service.EnhanceServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 乌草坡
 * @since 1.0
 */
@Service
public class SysUserService extends EnhanceServiceImpl<TSysUser> implements ISysUserService {

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
        String saltPassword =  MarkbenConstant.MD5_SALT + password;
        String md5Password = SecurityUtils.md5(saltPassword);
        return md5Password;
    }

    @Override
    public void changePwd(String userId, String newPassword, String oldPassword) {

    }

    @Override
    public TSysUser getUser(String username) {
        return null;
    }

    @Override
    public IResultResponse<IUserInfo> login(String username, String password) {
        return null;
    }
}
