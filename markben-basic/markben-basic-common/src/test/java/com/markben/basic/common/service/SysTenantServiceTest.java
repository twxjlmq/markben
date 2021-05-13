package com.markben.basic.common.service;

import com.markben.basic.common.ApplicationBasicCommonTest;
import com.markben.basic.common.entity.TSysTenant;
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
public class SysTenantServiceTest {

    @Autowired
    private ITenantService corpService;

    @Test
    public void saveTest() {
        TSysTenant corp = new TSysTenant();
        corp.setName("Markben");
        corp.setShortName("markben");
        corp.setTenantUserId("admin");
        boolean is = corpService.save(corp);
        Assert.assertTrue(is);
    }

}
