package com.markben.restful.common.controller;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.markben.cache.utils.CacheManagerUtils;
import com.markben.common.utils.LoggerUtils;
import com.markben.common.utils.StringUtils;
import com.markben.restful.common.helper.HttpRequestHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Properties;

/**
 * 获取验证码接口
 * @author 乌草坡 2022-01-30
 * @since 0.0.2
 */
@RestController
@RequestMapping(value = "/rest/captha/")
@Api(tags = "获取验证码接口")
public class RestCaptchaController extends AbstractRestController {

    private Producer producer;

    public static final String CAPTCHA_KEY_PREFIX = "captcha_";

    public static final String CAPTCHA_SESSION_KEY = "captcha_session_value";

    private static final long VALIDATE_TIME =  5 * 60 * 1000; //5分钟

    public RestCaptchaController(@Nullable Producer producer) {
        super();
        if(null == producer) {
            this.producer = createKaptcha();
        } else {
            this.producer = producer;
        }
    }

    /**
     * 获取登录的验证码
     * @param request 请求对象
     * @param response 响应对象
     * @param token token值
     */
    @GetMapping(value = "/login")
    @ApiOperation(value = "获取登录的验证码", tags = "获取登录的验证码接口")
    public void login(HttpServletRequest request, HttpServletResponse response, String token) {
        createCaptcha(request, token, response);
    }

    /**
     * 获取注册得验证码
     * @param request 请求对象
     * @param response 响应对象
     * @param token token值
     */
    @GetMapping(value = "/register")
    @ApiOperation(value = "获取注册的验证码", tags = "获取注册的验证码接口")
    public void register(HttpServletRequest request, HttpServletResponse response, String token) {
        createCaptcha(request, token, response);
    }

    /**
     * 创建生成验证码
     * @param request 请求对象
     * @param token token值
     * @param response 响应对象
     */
    private void createCaptcha(HttpServletRequest request, String token, HttpServletResponse response) {
        String code = producer.createText();
        if(StringUtils.isNotEmpty(token)) {
            String newToken = CAPTCHA_KEY_PREFIX + token;
            CacheManagerUtils.putCache(newToken, code, VALIDATE_TIME);
        } else {
            HttpRequestHelper.setSession(request, CAPTCHA_SESSION_KEY, code);
        }
        BufferedImage bufferedImage = producer.createImage(code);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            ImageIO.write(bufferedImage, "png", outputStream);
        } catch (Exception ex) {
            LoggerUtils.error(getLogger(), ex);
        } finally {
            if(null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    LoggerUtils.error(getLogger(), e);
                }
            }
        }
    }

    /**
     * 设置参数
     * @return
     */
    private DefaultKaptcha createKaptcha() {
        DefaultKaptcha captchaProducer = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "yes");// 图片边框
        properties.setProperty("kaptcha.border.color", "20,15,90");// 边框颜色
        properties.setProperty("kaptcha.textproducer.font.color", "blue"); // 字体颜色
        properties.setProperty("kaptcha.image.width", "110");// 图片宽
        properties.setProperty("kaptcha.image.height", "40");// 图片高
        properties.setProperty("kaptcha.textproducer.font.size", "30");// 字体大小
        properties.setProperty("kaptcha.session.key", "code");// session key
        properties.setProperty("kaptcha.textproducer.char.length", "4");// 验证码长度
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");// 字体
        Config config = new Config(properties);
        captchaProducer.setConfig(config);
        return captchaProducer;
    }
}
