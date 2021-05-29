package com.markben.basic.common.service;

import com.markben.basic.common.ApplicationBasicCommonTest;
import com.markben.basic.common.entity.TSysTenantUser;
import com.markben.beans.entity.TSysUser;
import com.markben.beans.service.UserService;
import com.markben.common.enums.YesOrNoType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 乌草坡
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBasicCommonTest.class)
public class SysUserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private TenantUserService corpUserService;

    @Test
    public void saveTest() {

        String tenantId = "markben_bae8885699a24246bba5491e4964dd2e";

        userService.delete("bbe598f7b4b6495ea445dc245865567b");

        TSysUser user = new TSysUser();
        user.setPassword("123456");
        user.setUsername("admin");
        user.setRemarks("系统超级管理员");

        boolean is = userService.save(user);
        Assert.assertTrue(is);

        TSysTenantUser corpUser = new TSysTenantUser();
        corpUser.setIsDefault(YesOrNoType.YES.getIndex());
        corpUser.setIsSuperAdmin(YesOrNoType.YES.getIndex());
        corpUser.setNickname("超级管理员");
        corpUser.setUserId(user.getId());
        corpUser.setTenantId(tenantId);

        is = corpUserService.save(corpUser);
        Assert.assertTrue(is);

    }

}
