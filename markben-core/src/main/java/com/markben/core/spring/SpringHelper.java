package com.markben.core.spring;

import com.markben.common.utils.CollectionUtils;
import org.springframework.core.OrderComparator;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.annotation.Order;

import java.util.*;

/**
 * Spring相关辅助类
 * @author 乌草坡
 * @since 0.0.1
 */
public class SpringHelper {

    /**
     * 对象列表排序，支持排序的类需要实现spring的{@link Ordered}接口
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> objectOrdered(Collection<T> list) {
        if(CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<T> orderedList = new ArrayList<>();
        List<T> unorderedList = new ArrayList<>();
        for(T t : list) {
            if(t instanceof Ordered || t.getClass().isAnnotationPresent(Order.class)) {
                orderedList.add(t);
            } else {
                unorderedList.add(t);
            }
        }
        Comparator<Object> comparator = AnnotationAwareOrderComparator.INSTANCE;
        Collections.sort(orderedList, comparator);
        if(CollectionUtils.isNotEmpty(unorderedList)) {
            orderedList.addAll(unorderedList);
        }
        return orderedList;
    }

}
