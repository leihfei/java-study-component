package com.lnlr.common.utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;

/**
 * @author:leihfei
 * @description 利用反射，将更新的对象的值进行比对存入
 * @date:Create in 0:14 2018/10/25
 * @email:leihfein@gmail.com
 */
public class CopyUtils {

    public static <T, V> void copy(T t, V v) throws IllegalAccessException {
        // 得到参数的字段
        Field[] sourceFields = v.getClass().getDeclaredFields();
        // 待保存的目标字段
        Field[] targetFields = t.getClass().getDeclaredFields();
        for (Field field : sourceFields) {
            field.setAccessible(true);
            Object value = field.get(v);
            if (value != null) {
                // 表示有值，那么需要将值写入到t对象中
                for (Field target : targetFields) {
                    target.setAccessible(true);
                    if (target.getName().equals(field.getName())) {
                        // 表示为同一个字段，那么需要对目标进行赋值
                        target.set(t, value);
                    }
                }
            }
        }
    }

    /**
     * @param t 源对象
     * @param v 目标对象
     * @return V 目标对象
     * @author: leihfei
     * @description 对象属性的拷贝
     * @date: 11:19 2018/10/30
     * @email: leihfein@gmail.com
     */
    public static <T, V> V beanCopy(T t, V v) {
        BeanUtils.copyProperties(t, v);
        return v;
    }
}
