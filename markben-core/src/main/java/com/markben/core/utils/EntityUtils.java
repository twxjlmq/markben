package com.markben.core.utils;

import com.markben.common.constant.MarkbenConstant;
import com.markben.common.utils.CollectionUtils;
import com.markben.common.utils.StringUtils;
import com.markben.core.bean.PKPrefix;
import com.markben.core.bean.PKStringEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 实体类工具
 * @author 乌草坡
 * @since 0.0.1
 */
public class EntityUtils {

    /**
     * 提取实体列表的主键ID，并转换为字符串数组
     * @param entities 实体列表
     * @return 返回实体列表对应的主键ID数组
     */
    public static String[] extractIdArray(List<? extends PKStringEntity> entities) {
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
    public static <T extends PKStringEntity, R> String[] extractIdArray(List<T> entities, Function<T, String> function) {
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
    public static Collection<String> extractIdList(List<? extends PKStringEntity> entities) {
        return new ArrayList<String>(extractIdSet(entities, PKStringEntity::getId));
    }
    
    /**
     * 提取实体列表的主键ID，并转换为字符串列表
     * @param entities 实体列表
     * @param function 函数式方法
     * @return 返回实体列表对应<code>function</code>参数指定的属性列表
     */
    public static <T extends PKStringEntity, R> Set<R> extractIdSet(List<T> entities, Function<T, R> function) {
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
    public static String createId(PKStringEntity entity) {
        String id = StringUtils.createSerialNum();
        if(entity instanceof PKPrefix) {
            String prefix = ((PKPrefix) entity).getPrefix();
            if(StringUtils.isNotEmpty(prefix)) {
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
    public static void createEntityId(PKStringEntity entity) {
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

    /**
     * 实体属性名称转化为数据库字段名称；
     * 注：默认为驼峰名词转化为下划线分隔的名称；
     * 如：userId ===> user_id
     * @param propertyName 实体属性名称
     * @return 返回对应数据库字段的名称
     */
    public static String toDbField(String propertyName) {
        return StringUtils.toUnderlineCase(propertyName);
    }
}
