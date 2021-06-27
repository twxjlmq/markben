package com.markben.personal.rest.test.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.markben.basic.rest.vo.LoginRequest;
import com.markben.beans.enums.MarkbenStatusEnums;
import com.markben.common.utils.JSONUtils;
import com.markben.personal.rest.test.main.SpringBootMain;
import com.markben.personal.rest.vo.LoginResultVO;
import com.markben.rest.common.response.RestResultResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * 用户REST接口测试类
 * @author 乌草坡
 * @since 0.0.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootMain.class)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestPersonalControllerTest {

    @Autowired
    private MockMvc mvc;


    private static final String URL_PREFIX = "/rest/personal/";

    private static int count = 0;

    @Before
    public void createOperateAuth() throws Exception {
        if(count > 0) {
            return;
        }
        count++;
    }

    @Test
    public void aLoginTest() throws Exception {
        String url = URL_PREFIX + "login";

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("123456");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONUtils.toJson(loginRequest))
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        System.out.println("status:" + status);
        Assert.assertEquals(200, status);
        String content = response.getContentAsString();
        System.out.println("content:" + content);

        RestResultResponse<LoginResultVO> restResp = JSONUtils.fromJson(content, new TypeReference<RestResultResponse<LoginResultVO>>() {});
        Assert.assertEquals(restResp.getStatus(), MarkbenStatusEnums.SUCCESS.getStatus());
        Assert.assertNotNull(restResp.getResult());
        System.out.println("nickname:" + restResp.getResult().getNickname());
    }

}
