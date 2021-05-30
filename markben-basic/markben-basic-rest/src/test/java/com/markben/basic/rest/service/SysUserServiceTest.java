package com.markben.basic.rest.service;

import com.markben.basic.rest.ApplicationBasicCommonTest;
import com.markben.basic.rest.entity.TSysUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 乌草坡
 * @since 0.0.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBasicCommonTest.class)
public class SysUserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void saveTest() {
        userService.delete("bbe598f7b4b6495ea445dc245865567b");
        TSysUser user = new TSysUser();
        user.setPassword("123456");
        user.setUsername("admin");
        user.setRemarks("系统超级管理员");
        boolean is = userService.save(user);
        Assert.assertTrue(is);

    }

}
