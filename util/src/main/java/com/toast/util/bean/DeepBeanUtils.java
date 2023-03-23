package com.toast.util.bean;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author 土司先生
 * @time 2023/3/23
 * @describe Bean对象深拷贝
 */
public class DeepBeanUtils extends BeanUtils {
    private DeepBeanUtils() {}

    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T obj = target.get();
            copyProperties(source, obj); // 属性拷贝
            list.add(obj); // 添加数据
        }
        return list;
    }
}
