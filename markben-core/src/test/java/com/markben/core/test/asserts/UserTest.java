package com.markben.core.test.asserts;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.markben.common.enums.YesNoType;
import com.markben.core.test.ApplicationTest;
import com.markben.core.test.entity.TTestUser;
import com.markben.core.test.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @autor 乌草坡 2020-02-28
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
public class UserTest {

    @Autowired
    private UserService userServ;

    @Test
    public void saveTest() {
        TTestUser user = new TTestUser();
        user.setId("11122");
        user.setAge(30);
        user.setFullName("李四");
        user.setCreateTime(new Date());
        userServ.save(user);
    }

    @Test
    public void saveBatchTest() {
        TTestUser user = new TTestUser();
        user.setId("111222");
        user.setAge(30);
        user.setFullName("李四");
        user.setCreateTime(new Date());
        List<TTestUser> list = new ArrayList<>();
        list.add(user);
        userServ.saveOrUpdateBatch(list, 10);
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

    @Test
    public void queryTest() {


        /*QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select().eq("state", YesNoType.YES.getIndex());
        //QueryChainWrapper queryWrapper = super.query().eq("state", YesNoType.YES.getIndex());
        TTestUser user = userServ.getOne(queryWrapper, false);
        Assert.assertNotNull(user);*/

        Optional<TTestUser> userOpt = userServ.getUserOfLogin("zhansan");
        Assert.assertTrue(userOpt.isPresent());
        TTestUser user = userOpt.get();
        System.out.println(user.getFullName());
    }

}
