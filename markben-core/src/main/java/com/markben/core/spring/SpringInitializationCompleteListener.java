package com.markben.core.spring;

import com.markben.common.logger.Logger;
import com.markben.common.utils.LoggerUtils;
import com.markben.core.config.DefaultMarkbenConfiguration;
import com.markben.core.config.MarkbenConfiguration;
import com.markben.core.context.MarkbenContextAware;
import com.markben.core.context.MarkbenContextFactory;
import com.markben.core.initialization.MarkbenInitializeListener;
import com.markben.core.initialization.MarkbenInitializationImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Spring初始化结束后执行该类中的方法
 * @autor 乌草坡
 * @since 0.0.1
 */
@Component
public class SpringInitializationCompleteListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerUtils.getLogger(SpringInitializationCompleteListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        if(null != context && null == context.getParent()) {
            LoggerUtils.debug(logger, "正在初始化，自定义初始化类....");
            SpringMarkbenContext markbenContext = new SpringMarkbenContext(context);
            MarkbenContextFactory.setContext(markbenContext);
            //判断有没有配置实现类
            MarkbenConfiguration configuration = MarkbenContextFactory.find(MarkbenConfiguration.class);
            if(null == configuration) {
                configuration = new DefaultMarkbenConfiguration(markbenContext);
                markbenContext.put(configuration);
            } else {
                if(configuration instanceof MarkbenContextAware) {
                    MarkbenContextAware contextAware = (MarkbenContextAware)configuration;
                    contextAware.setContext(markbenContext);
                }
            }
            if(configuration instanceof MarkbenInitializeListener) {
                ((MarkbenInitializeListener) configuration).initialize();
            }
            MarkbenContextFactory.setConfiguration(configuration);
            new MarkbenInitializationImpl().init();
        }
    }
}
