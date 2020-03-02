package com.markben.core.test;

import com.markben.core.ApplicationTest;
import com.markben.core.entity.TUser;
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
        TUser user = new TUser();
        user.setId("11122");
        user.setAge(30);
        user.setName("李四");
        user.setCreateTime(new Date());
        userServ.save(user);
    }

    @Test
    public void getTest() {
        TUser user = userServ.getById("11111");
        System.out.println(user.getName());
        Assert.assertNotNull(user);
    }

}
