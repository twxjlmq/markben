package com.markben.core.test;

import com.markben.core.ApplicationTest;
import com.markben.core.entity.TTestUser;
import com.markben.core.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @autor 乌草坡 2020-02-28
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
public class UserTest {

    @Autowired
    private UserService userServ;

    //@Test
    public void saveTest() {
        TTestUser user = new TTestUser();
        user.setId("11122");
        user.setAge(30);
        user.setFullName("李四");
        user.setCreateTime(new Date());
        userServ.save(user);
    }

    //@Test
    public void getTest() {
        TTestUser user = userServ.getById("11111");
        System.out.println(user.getFullName());
        Assert.assertNotNull(user);
    }

    @Test
    public void updateUser() {
        TTestUser user = userServ.getById("11111");
        System.out.println(user.getFullName());
        user.setAge(100);
        boolean is = userServ.updateById(user);
        Assert.assertTrue(is);
    }

}
