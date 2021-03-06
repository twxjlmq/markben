package com.markben.core.service.event;

import com.markben.common.logger.Logger;
import com.markben.common.utils.CollectionUtils;
import com.markben.common.utils.LoggerUtils;
import com.markben.core.context.MarkbenContextFactory;
import com.markben.core.dao.BaseDao;

import java.util.List;

/**
 * 业务行为事件监听上下文
 * @author 乌草坡
 * @since 0.0.1
 */
public class BizBehaviourEventListenerContext {

    private static BizBehaviourEventListenerContext instance = new BizBehaviourEventListenerContext();
    
    private static final Logger logger = LoggerUtils.getLogger(BizBehaviourEventListenerContext.class);
    
    private static List<BizBehaviourEvent> events;
    
    private BizBehaviourEventListenerContext() {
        
    }
    
    /**
     * 获取实例
     * @return
     */
    public static BizBehaviourEventListenerContext getInstance() {
        if(null == events) {
            synchronized (instance) {
                if(null == events) {
                    instance.init();
                }
            }
        }
        return instance;
    }

    /**
     * 触发事件
     * @param mapper BaseEnhanceMapper对象
     * @param behaviourType Dao行为类型；详情请参考{@link BizBehaviourType}
     * @param args 参数（有可能是实体类，如：当<code>behaviourType</code>为：{@link BizBehaviourType#SAVE}时）
     */
    public void trigger(BaseDao mapper, BizBehaviourType behaviourType, Object args) {
        LoggerUtils.debug(logger, "触发DAO事件, 事件类型为：[{}].", behaviourType.getText());
        if(CollectionUtils.isNotEmpty(events)) {
            for(BizBehaviourEvent event : events) {
                event.event(mapper, behaviourType, args);
            }
        } else {
            LoggerUtils.debug(logger, "未发现触发事件处理类.");
        }
    }

    private void init() {
        events = MarkbenContextFactory.finds(BizBehaviourEvent.class);
    }
    
}
