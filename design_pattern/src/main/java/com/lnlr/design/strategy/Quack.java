package com.lnlr.design.strategy;

import com.lnlr.design.strategy.intface.QuackBehavior;

/**
 * @author:leihfei
 * @description 定义一种叫的实现
 * @date:Create in 17:52 2018/9/9
 * @email:leihfein@gmail.com
 */
public class Quack implements QuackBehavior {

    /**
     * 这是哥哥叫声
     */
    public void quack() {
        System.out.println("格格叫");
    }
}
