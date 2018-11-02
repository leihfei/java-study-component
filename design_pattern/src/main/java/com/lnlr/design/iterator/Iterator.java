package com.lnlr.design.iterator;

/**
 * @author:leihfei
 * @description 迭代器接口
 * @date:Create in 22:36 2018/10/25
 * @email:leihfein@gmail.com
 */
public interface Iterator<T> {
    void hasNext();

    T next();
}
