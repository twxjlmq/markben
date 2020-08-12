package com.markben.basic.common.service;

import com.markben.basic.common.ApplicationBasicCommonTest;
import com.markben.basic.common.entity.TSysCorp;
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
public class SysCorpServiceTest {

    @Autowired
    private ISysCorpService corpService;

    @Test
    public void saveTest() {
        TSysCorp corp = new TSysCorp();
        corp.setName("Markben");
        corp.setShortName("markben");
        corp.setCorpUserId("admin");
        boolean is = corpService.save(corp);
        Assert.assertTrue(is);
    }

}
