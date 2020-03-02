package com.markben.core.spring;

import com.markben.common.utils.CollectionUtils;
import org.springframework.core.OrderComparator;
import org.springframework.core.Ordered;

import java.util.*;

/**
 * Spring相关辅助类
 * @autor 乌草坡
 * @since 1.0
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
            if(t instanceof Ordered) {
                orderedList.add(t);
            } else {
                unorderedList.add(t);
            }
        }
        Comparator<Object> comparator = OrderComparator.INSTANCE;
        Collections.sort(orderedList, comparator);
        if(CollectionUtils.isNotEmpty(unorderedList)) {
            orderedList.addAll(unorderedList);
        }
        return orderedList;
    }

}
