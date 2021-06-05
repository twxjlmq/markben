package com.markben.org.common.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.markben.basic.common.entity.TSysUser;
import com.markben.basic.common.service.UserService;
import com.markben.common.utils.CollectionUtils;
import com.markben.common.utils.LoggerUtils;
import com.markben.core.service.MgrServiceImpl;
import com.markben.core.utils.EntityUtils;
import com.markben.org.common.entity.TSysDepartment;
import com.markben.org.common.entity.TSysDeptUser;
import com.markben.org.common.service.DepartmentService;
import com.markben.org.common.service.DeptUserService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 部门用户服务实现类
 * @author 乌草坡
 * @since 0.0.1
 */
@Service
public class DeptUserServiceImpl extends MgrServiceImpl<TSysDeptUser> implements DeptUserService {

    private DepartmentService deptService;

    private UserService userService;

    public DeptUserServiceImpl(DepartmentService deptService, UserService userService) {
        this.deptService = deptService;
        this.userService = userService;
    }

    @Override
    public List<TSysDepartment> getDepartmentList(String userId) {
        LoggerUtils.debug(getLogger(), "正在获取用户ID[{}]所在的部门列表...", userId);
        List<TSysDeptUser> relateList = super.finds(EntityUtils.toDbField("userId"), userId);
        if(CollectionUtils.isNotEmpty(relateList)) {
            String[] deptIds = relateList.stream().map(TSysDeptUser::getDeptId).toArray(String[]::new);
            return deptService.findListByValid(deptIds);
        }
        return Collections.emptyList();
    }

    @Override
    public List<TSysUser> getCorpUserList(String deptId) {
        LoggerUtils.debug(getLogger(), "正在获取部门ID[{}]下的所有用户列表...", deptId);
        List<TSysDeptUser> relateList = super.finds(EntityUtils.toDbField("deptId"), deptId);
        if(CollectionUtils.isNotEmpty(relateList)) {
            String[] deptIds = relateList.stream().map(TSysDeptUser::getUserId).toArray(String[]::new);
            return userService.findListByValid(deptIds);
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<TSysDepartment> getDepartment(String deptId, String userId) {
        LoggerUtils.debug(getLogger(), "正在获取部门ID为[{}]，用户ID为[{}]的部门信息...", deptId, userId);
        QueryChainWrapper queryWrapper = super.query().eq(EntityUtils.toDbField("deptId"), deptId)
                .and(w -> w.eq(EntityUtils.toDbField("userId"), userId));
        TSysDeptUser deptUser = super.getOne(queryWrapper, false);
        if(null != deptUser) {
            return deptService.find(deptUser.getDeptId());
        }
        return Optional.empty();
    }
}
