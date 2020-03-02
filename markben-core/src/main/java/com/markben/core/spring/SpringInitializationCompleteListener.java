package com.markben.core.spring;

import com.markben.common.logger.ILogger;
import com.markben.common.utils.LoggerUtils;
import com.markben.core.context.MarkbenContextFactory;
import com.markben.core.initialization.MarkbenInitializationImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Spring初始化结束后执行该类
 * @autor 乌草坡
 * @since 1.0
 */
public class SpringInitializationCompleteListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final ILogger logger = LoggerUtils.getLogger(SpringInitializationCompleteListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        if(null != context && null == context.getParent()) {
            LoggerUtils.debug(logger, "正在初始化，自定义初始化类....");
            SpringMarkbenContext markbenContext = new SpringMarkbenContext(context);
            MarkbenContextFactory.setContext(markbenContext);
            new MarkbenInitializationImpl().init();
        }
    }
}
