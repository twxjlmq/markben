package com.markben.core.initialization;

import com.markben.common.SystemInfo;
import com.markben.common.config.SystemConfig;
import org.springframework.stereotype.Component;

/**
 * 默认的系统本部信息实现类
 * @author 乌草坡
 * @since 0.0.1
 */
@Component
public class DefaultSystemVersionInfo implements MarkbenSystemVersion {

    @Override
    public SystemInfo getSystemInfo() {
        return SystemConfig.getInstance().getSystemInfo();
    }
}
