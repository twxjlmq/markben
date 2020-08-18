package com.markben.core.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.markben.common.logger.ILogger;
import com.markben.common.utils.CollectionUtils;
import com.markben.common.utils.LoggerUtils;
import com.markben.common.utils.StringUtils;
import com.markben.core.bean.*;
import com.markben.core.mapper.BaseEnhanceMapper;
import com.markben.core.service.event.BizBehaviourEventListenerContext;
import com.markben.core.service.event.BizBehaviourType;
import com.markben.core.utils.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 增强服务实现类
 * @autor 乌草坡 2020-02-28
 * @since 1.0
 */
public class EnhanceServiceImpl<T extends IEntityBean> extends ServiceImpl<BaseEnhanceMapper<T>, T> implements IMgrService<T> {

    private ILogger logger;

    @Autowired
    private BaseEnhanceMapper<T> baseMapper;

    public EnhanceServiceImpl() {
        logger = LoggerUtils.getLogger(getClass());
    }

    @Override
    public BaseEnhanceMapper<T> getBaseMapper() {
        return baseMapper;
    }

    @Override
    public T find(String id) {
        return getById(id);
    }

    @Override
    public List<T> finds(String[] ids) {
        return finds("id", ids);
    }

    @Override
    public List<T> finds(String columnName, Object value) {
        if(null == value) {
            return Collections.EMPTY_LIST;
        }
        Wrapper<T> wrapper = null;
        if(value.getClass().isArray()) {
            Object[] values = (Object[]) value;
            wrapper = query().in(columnName, values);
        } else {
            wrapper = query().eq(columnName, value);
        }
        return getBaseMapper().selectList(wrapper);
    }

    @Override
    @Transactional
    public boolean save(T entity) {
        handleSaveEntity(entity);
        boolean is = super.save(entity);
        triggerBehaviourEvent(BizBehaviourType.SAVE, entity);
        return is;
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<T> entityList) {
        if(CollectionUtils.isEmpty(entityList)) {
            LoggerUtils.error(getLogger(), "实体对象列表为空，无法批量保存");
            return false;
        }
        handleSaveEntity(entityList);
        boolean is = super.saveBatch(entityList);
        triggerBehaviourEvent(BizBehaviourType.SAVE, entityList);
        return is;
    }

    @Override
    @Transactional
    public boolean saveOrUpdateBatch(Collection<T> entityList) {
        if(CollectionUtils.isEmpty(entityList)) {
            LoggerUtils.error(getLogger(), "实体对象列表为空，无法批量保存或更新");
            return false;
        }
        handleUpdateEntity(entityList);
        handleSaveEntity(entityList);
        return super.saveOrUpdateBatch(entityList);
    }

    @Override
    @Transactional
    public boolean updateById(T entity) {
        StringUtils.isAssert(entity, "entity参数不能为空");
        handleUpdateEntity(entity);
        boolean is = super.updateById(entity);
        triggerBehaviourEvent(BizBehaviourType.UPDATE, entity);
        return is;
    }

    @Override
    @Transactional
    public boolean update(Wrapper<T> updateWrapper) {
        StringUtils.isAssert(updateWrapper, "updateWrapper参数不能为空");
        T entity = updateWrapper.getEntity();
        if(null != entity) {
            handleUpdateEntity(entity);
        }
        boolean is = super.update(updateWrapper);
        triggerBehaviourEvent(BizBehaviourType.UPDATE, entity);
        return is;
    }

    @Override
    @Transactional
    public boolean update(T entity, Wrapper<T> updateWrapper) {
        StringUtils.isAssert(entity, "entity参数不能为空");
        handleUpdateEntity(entity);
        boolean is = super.update(entity, updateWrapper);
        triggerBehaviourEvent(BizBehaviourType.UPDATE, entity);
        return is;
    }

    @Override
    @Transactional
    public boolean updateBatchById(Collection<T> entityList) {
        if(CollectionUtils.isEmpty(entityList)) {
            LoggerUtils.error(getLogger(), "实体对象列表为空，无法批量更新");
            return false;
        }
        handleUpdateEntity(entityList);
        boolean is = super.updateBatchById(entityList);
        triggerBehaviourEvent(BizBehaviourType.UPDATE, entityList);
        return is;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(T entity, Wrapper<T> updateWrapper) {
        StringUtils.isAssert(entity, "entity参数不能为空");
        handleUpdateEntity(entity);
        handleSaveEntity(entity);
        return super.saveOrUpdate(entity, updateWrapper);
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        if(CollectionUtils.isEmpty(entityList)) {
            LoggerUtils.error(getLogger(), "实体对象列表为空，无法批量保存");
            return false;
        }
        handleSaveEntity(entityList);
        boolean is = super.saveBatch(entityList, batchSize);
        triggerBehaviourEvent(BizBehaviourType.UPDATE, entityList);
        return is;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(T entity) {
        StringUtils.isAssert(entity, "entity参数不能为空");
        handleUpdateEntity(entity);
        handleSaveEntity(entity);
        return super.saveOrUpdate(entity);
    }

    @Override
    @Transactional
    public boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {
        if(CollectionUtils.isEmpty(entityList)) {
            LoggerUtils.error(getLogger(), "实体对象列表为空，无法批量保存或更新");
            return false;
        }
        handleUpdateEntity(entityList);
        handleSaveEntity(entityList);
        return super.saveOrUpdateBatch(entityList, batchSize);
    }

    @Override
    @Transactional
    public boolean updateBatchById(Collection<T> entityList, int batchSize) {
        if(CollectionUtils.isEmpty(entityList)) {
            LoggerUtils.error(getLogger(), "实体对象列表为空，无法批量更新");
            return false;
        }
        handleUpdateEntity(entityList);
        boolean is = super.updateBatchById(entityList, batchSize);
        triggerBehaviourEvent(BizBehaviourType.UPDATE, entityList);
        return is;
    }

    @Override
    public T getOne(Wrapper<T> queryWrapper, boolean throwEx) {
        if (throwEx) {
            return getBaseMapper().selectOne(queryWrapper);
        }
        return SqlHelper.getObject(log, getBaseMapper().selectList(queryWrapper));
    }

    @Override
    public Map<String, Object> getMap(Wrapper<T> queryWrapper) {
        return SqlHelper.getObject(log, getBaseMapper().selectMaps(queryWrapper));
    }

    @Override
    @Transactional
    public boolean removeById(Serializable id) {
        boolean is = super.removeById(id);
        triggerBehaviourEvent(BizBehaviourType.DELETE, id);
        return is;
    }

    @Override
    @Transactional
    public boolean remove(Wrapper<T> queryWrapper) {
        boolean is = super.remove(queryWrapper);
        triggerBehaviourEvent(BizBehaviourType.DELETE, queryWrapper);
        return is;
    }

    @Override
    @Transactional
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        boolean is = super.removeByIds(idList);
        triggerBehaviourEvent(BizBehaviourType.DELETE, idList);
        return is;
    }

    @Override
    @Transactional
    public boolean save(Collection<T> ts) {
        return saveBatch(ts);
    }

    @Override
    @Transactional
    public boolean update(T t) {
        return updateById(t);
    }

    @Override
    @Transactional
    public boolean update(Collection<T> ts) {
        return updateBatchById(ts);
    }

    @Override
    @Transactional
    public boolean delete(T t) {
        if(null == t) {
            return false;
        }
        Serializable id = null;
        if(t instanceof IPKEntityBean) {
            id = ((IPKEntityBean)t).getId();
        }
        if(null == id) {
            return false;
        }
        return removeById(id);
    }

    @Override
    @Transactional
    public boolean delete(Collection<T> ts) {
        if(CollectionUtils.isEmpty(ts)) {
            return false;
        }
        Set<Serializable> ids = ts.stream().filter(t -> t instanceof  IPKEntityBean)
                .map(bean -> ((IPKEntityBean) bean).getId()).collect(Collectors.toSet());
        return removeByIds(ids);
    }

    @Override
    @Transactional
    public boolean delete(String id) {
        if(StringUtils.isEmpty(id)) {
            return false;
        }
        return delete(StringUtils.stringToArray(id));
    }

    @Override
    @Transactional
    public boolean delete(String[] ids) {
        if(null == ids || ids.length == 0) {
            return false;
        }
        return removeByIds(Arrays.asList(ids));
    }

    /**
     * 处理保存实体
     * @param entity
     */
    private void handleSaveEntity(T entity) {
        if(entity instanceof IPKStringEntity) {
            IPKStringEntity pkStrEntity = (IPKStringEntity) entity;
            String id = pkStrEntity.getId();
            if(StringUtils.isEmpty(id)) {
                EntityUtils.createEntityId(pkStrEntity);
            }
        }
        if(entity instanceof ICreateTime && null == ((ICreateTime)entity).getCreateTime()) {
            ((ICreateTime)entity).setCreateTime(new Date());
        }
    }

    /**
     * 处理保存实体
     * @param entities
     */
    private void handleSaveEntity(Collection<T> entities) {
        for(T entity : entities) {
            handleSaveEntity(entity);
        }
    }

    /**
     * 处理更新实体类
     * @param entity
     */
    private void handleUpdateEntity(T entity) {
        if(entity instanceof IPKEntityBean) {
            IPKEntityBean pkEntity = (IPKEntityBean) entity;
            Serializable id = pkEntity.getId();
            if(null != id) {
                handleUpdateTimeEntity(entity);
            }
        } else {
            handleUpdateEntity(entity);
        }
    }

    /**
     * 处理更新日期
     * @param entity
     */
    private void handleUpdateTimeEntity(T entity) {
        if (entity instanceof IUpdateTime && null == ((IUpdateTime) entity).getUpdateTime()) {
            ((IUpdateTime) entity).setUpdateTime(new Date());
        }
    }

    /**
     * 处理更新实体类
     * @param entities
     */
    private void handleUpdateEntity(Collection<T> entities) {
        for(T entity : entities) {
            handleUpdateEntity(entity);
        }
    }

    /**
     * 触发行为事件
     * @param behaviourType 行为类型
     * @param value 值（可能是实体类或ID）
     */
    private void triggerBehaviourEvent(BizBehaviourType behaviourType, Object value) {
        BizBehaviourEventListenerContext.getInstance().trigger(getBaseMapper(), behaviourType, value);
    }

    protected ILogger getLogger() {
        return logger;
    }
}
