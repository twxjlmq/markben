package com.markben.core.spring;

import com.markben.common.SystemInfo;
import com.markben.common.logger.Logger;
import com.markben.common.utils.LoggerUtils;
import com.markben.core.config.DefaultMarkbenConfiguration;
import com.markben.core.config.MarkbenConfiguration;
import com.markben.core.context.MarkbenContextAware;
import com.markben.core.context.MarkbenContextFactory;
import com.markben.core.initialization.MarkbenInitializeListener;
import com.markben.core.initialization.MarkbenInitializationImpl;
import com.markben.core.initialization.MarkbenSystemVersion;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Spring初始化结束后执行该类中的方法
 * @author 乌草坡
 * @since 0.0.1
 */
@Component
public class SpringInitializationCompleteListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerUtils.getLogger(SpringInitializationCompleteListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        if(null != context && null == context.getParent()) {
            LoggerUtils.debug(logger, "正在初始化Markben....");
            SpringMarkbenContext markbenContext = new SpringMarkbenContext(context);
            MarkbenContextFactory.setContext(markbenContext);
            printSystemVersion();
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

    private void printSystemVersion() {
        MarkbenSystemVersion systemVersion = MarkbenContextFactory.find(MarkbenSystemVersion.class);
        if(null == systemVersion) {
            return;
        }
        LoggerUtils.debug(logger, "系统信息提供者实现类为:[{}].", systemVersion.getClass());
        SystemInfo systemInfo = systemVersion.getSystemInfo();
        if(null == systemInfo) {
            LoggerUtils.warn(logger, "未获取到系统信息.");
            return;
        }
        printSystemVersionV1(systemInfo.getVersion());
    }

    private void printSystemVersionV1(String version) {
        System.out.println("  __  _   _                    _       _             ");
        System.out.println("/  _ / \\/_  \\            _    | |   _ | |                 ");
        System.out.println("| |  | |  | |   ___   _ | |_ _| | / / | |  ___     ___     ____  ");
        System.out.println("| |  | |  | | /  _  \\/  | /_ _| |/ /  | |/  _  \\ /  _  \\ /  __  \\");
        System.out.println("| |  | |  | ||  (_)  |  | |   | |\\ \\  | |  (_)  | = = =  | |  | |");
        System.out.println("|_|  |_|  |_| \\ ___ /\\_ |_|   |_| \\_\\ |_|_ ___ / \\ ___ / |_|  |_|");
        System.out.println("");
        System.out.println(">>> Markben >>> \t\t\t(" + version + ")");
        System.out.println("");
    }

    private void printSystemVersionV2(String version) {
        System.out.println("     ____          ____                      _       _     ");
        System.out.println("    / /\\ \\        / /\\ \\               _    | |   _ | |                 ");
        System.out.println("   / /  \\ \\      / /  \\ \\     ___   _ | |_ _| | / / | |  ___     ___     ____  ");
        System.out.println("  / /    \\ \\    / /    \\ \\  /  _  \\/  | /_ _| |/ /  | |/  _  \\ /  _  \\ /  __  \\");
        System.out.println(" / /      \\ \\  / /      \\ \\|  (_)  |  | |   | |\\ \\  | |  (_)  | = = =  | |  | |");
        System.out.println("/_/        \\_\\/_/        \\_\\\\ ___ /\\_ |_|   |_| \\_\\ |_|_ ___ / \\ ___ / |_|  |_|");
        System.out.println("");
        System.out.println(">>> Markben >>> \t\t\t("+version+")");
        System.out.println("");
    }
}
