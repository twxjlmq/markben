package com.markben.core.utils;

import com.markben.common.constant.MarkbenConstant;
import com.markben.common.utils.CollectionUtils;
import com.markben.common.utils.StringUtils;
import com.markben.core.bean.IPKPrefix;
import com.markben.core.bean.IPKStringEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 实体类工具
 * @author 乌草坡 2019年5月15日
 * @version 1.0
 * @since 1.0
 */
public class EntityUtils {

    /**
     * 提取实体列表的主键ID，并转换为字符串数组
     * @param entities 实体列表
     * @return 返回实体列表对应的主键ID数组
     */
    public static String[] extractIdArray(List<? extends IPKStringEntity> entities) {
        if(CollectionUtils.isEmpty(entities)) {
            return null;
        }
        String[] array = null;
        Collection<String> ids = extractIdList(entities);
        if(CollectionUtils.isNotEmpty(ids)) {
            array = new String[ids.size()];
            ids.toArray(array);
        }
        return array;
    }
    
    /**
     * 提取实体列表的主键ID，并转换为字符串数组
     * @param entities entities 实体列表
     * @param function 函数式方法
     * @return 返回实体列表对应<code>function</code>参数指定的属性列表
     */
    public static <T extends IPKStringEntity, R> String[] extractIdArray(List<T> entities, Function<T, String> function) {
        if(CollectionUtils.isEmpty(entities)) {
            return null;
        }
        return entities.stream().map(a -> function.apply(a)).toArray(String[]::new);
    }
    
    /**
     * 提取实体列表的主键ID，并转换为字符串列表
     * @param entities 实体列表
     * @return 返回实体列表对应的主键ID列表
     */
    public static Collection<String> extractIdList(List<? extends IPKStringEntity> entities) {
        return new ArrayList<String>(extractIdSet(entities, IPKStringEntity::getId));
    }
    
    /**
     * 提取实体列表的主键ID，并转换为字符串列表
     * @param entities 实体列表
     * @param function 函数式方法
     * @return 返回实体列表对应<code>function</code>参数指定的属性列表
     */
    public static <T extends IPKStringEntity, R> Set<R> extractIdSet(List<T> entities, Function<T, R> function) {
        if(CollectionUtils.isEmpty(entities)) {
            return null;
        }
        return entities.stream().map(a -> function.apply(a)).collect(Collectors.toSet());
    }
    
    /**
     * 生成ID
     * @param entity 实体对象
     * @return 返回生成后的ID
     */
    public static String createId(IPKStringEntity entity) {
        String id = StringUtils.createSerialNum();
        if(entity instanceof IPKPrefix) {
            String prefix = ((IPKPrefix) entity).getPrefix();
            if(StringUtils.isNotEmpty(prefix)) {
                prefix = prefix.toLowerCase();
                if(!prefix.endsWith(MarkbenConstant.COMBINE_VALUE_SEPARATOR)) {
                    prefix += MarkbenConstant.COMBINE_VALUE_SEPARATOR;
                }
                id = prefix + id;
            }
        }
        return id;
    }
    
    /**
     * 创建实体ID
     * @param entity 实体对象
     */
    public static void createEntityId(IPKStringEntity entity) {
        String id = createId(entity);
        assertId(id);
        entity.setId(id);
    }
    
    /**
     * 检测ID长度
     * @param id 主键ID
     */
    private static void assertId(String id) {
        if(id.length() > MarkbenConstant.ID_LENGTH) {
            throw new IllegalArgumentException("主键ID长度已超过50，请检查是否是前缀设置过长.");
        }
    }
}
