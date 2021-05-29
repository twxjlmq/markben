package com.markben.core.service.event;

import com.markben.common.logger.Logger;
import com.markben.common.utils.CollectionUtils;
import com.markben.common.utils.LoggerUtils;
import com.markben.core.callback.CallbackAware;
import com.markben.core.callback.DeleteCallbackAware;
import com.markben.core.callback.SaveCallbackAware;
import com.markben.core.callback.UpdateCallbackAware;
import com.markben.core.context.MarkbenContextFactory;
import com.markben.core.initialization.MarkbenInitializeListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 业务行为回调事件类
 * @author 乌草坡
 * @since 0.0.1
 */
@Component
public class BizBehaviourCallbackEvent implements BizBehaviourEvent, MarkbenInitializeListener {

    private static final Logger logger = LoggerUtils.getLogger(BizBehaviourCallbackEvent.class);
    
    @SuppressWarnings("rawtypes")
    private List<CallbackAware> callbacks;
    
    //用来存放回调实现类
    @SuppressWarnings("rawtypes")
    private Map<BizBehaviourType, Map<String, List<CallbackAware>>> callbackMap = new HashMap<BizBehaviourType, Map<String, List<CallbackAware>>>();
    //回调泛型参数类型名称缓存
    private Map<String, CallbackTypeProperty> callbackTypePropCache = new HashMap<String, CallbackTypeProperty>();
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void event(final Object target, final BizBehaviourType behaviourType, Object value) {
        Map<String, List<CallbackAware>> callbackAwareMap = callbackMap.get(behaviourType);
        if(null != callbackAwareMap) {
            Type[] types = target.getClass().getGenericInterfaces();
            String targetMapperName = types[0].getTypeName();
            List<CallbackAware> callbackAwareList = callbackAwareMap.get(targetMapperName);
            if(CollectionUtils.isNotEmpty(callbackAwareList)) {
                String targetEntityName = value.getClass().getTypeName();
                callbackAwareList.parallelStream().forEach(callbackAware -> {
                    String valueTypeName = callbackTypePropCache.get(callbackAware.getClass().getName()).getValueTypeName();
                    if((null == valueTypeName && callbackAware instanceof DeleteCallbackAware) || (null != valueTypeName && valueTypeName.equals(targetEntityName))) {
                        LoggerUtils.debug(logger, "正在执行[{}]的回调方法，实现类为：[{}].", behaviourType.getText(), callbackAware.getClass());
                        callbackAware.callback(target, value);
                    }
                });
            }
        }
        if(CollectionUtils.isNotEmpty(callbacks)) {
            callbacks.parallelStream().forEach(callback -> {
                LoggerUtils.debug(logger, "正在执行[{}]的回调方法，实现类为：[{}].", behaviourType.getText(), callback.getClass());
                callback.callback(target, value);
            });
        }
    }

    @Override
    public void initialize() {
        LoggerUtils.debug(logger, "正在初始化业务回调事件的注册...");
        callbacks = MarkbenContextFactory.finds(CallbackAware.class);
        int num = 0;
        if(CollectionUtils.isNotEmpty(callbacks)) {
            num = callbacks.size();
            handleClassifyCallback();
        }
        LoggerUtils.debug(logger, "业务回调事件注册[结束],共注册[{}]个实现类.", num);
    }
    
    /**
     * 处理分类的回调接口
     */
    @SuppressWarnings("rawtypes")
    private void handleClassifyCallback() {
        Set<CallbackAware> removeList = new HashSet<>();
        for(CallbackAware callbackAware : callbacks) {
            if(callbackAware instanceof SaveCallbackAware) {
                addClassifyCallback(BizBehaviourType.SAVE, removeList, callbackAware);
            }
            if(callbackAware instanceof UpdateCallbackAware) {
                addClassifyCallback(BizBehaviourType.UPDATE, removeList, callbackAware);
            }
            if(callbackAware instanceof DeleteCallbackAware) {
                addClassifyCallback(BizBehaviourType.DELETE, removeList, callbackAware);
            }
        }
        if(CollectionUtils.isNotEmpty(removeList)) {
            callbacks.removeAll(removeList);
        }
    } 
    
    /**
     * 添加对应分类的callback
     * @param type
     * @param removeList
     * @param callbackAware
     */
    @SuppressWarnings("rawtypes")
    private void addClassifyCallback(BizBehaviourType type, Set<CallbackAware> removeList, CallbackAware callbackAware) {
        Map<String, List<CallbackAware>> classifyCallbackMap =  callbackMap.get(type);
        if(null == classifyCallbackMap) {
            classifyCallbackMap = new HashMap<>();
            callbackMap.put(type, classifyCallbackMap);
        }
        Type[] types = ((ParameterizedType)callbackAware.getClass().getGenericInterfaces()[0]).getActualTypeArguments();
        String mapperName = types[0].getTypeName();
        String valueTypeName = null;
        if(types.length > 1) {
            valueTypeName = types[1].getTypeName();
        }
        callbackTypePropCache.put(callbackAware.getClass().getName(), new CallbackTypeProperty(mapperName, valueTypeName));
        List<CallbackAware> callbackList = classifyCallbackMap.get(mapperName);
        if(CollectionUtils.isEmpty(callbackList)) {
            callbackList = new ArrayList<>();
            classifyCallbackMap.put(mapperName, callbackList);
        }
        callbackList.add(callbackAware);
        removeList.add(callbackAware);
    }
}
