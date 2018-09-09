package com.lnlr.design.strategy;

import com.lnlr.design.strategy.intface.FlyBehavior;

/**
 * @author:leihfei
 * @description 定义一种不会飞的能力
 * @date:Create in 17:54 2018/9/9
 * @email:leihfein@gmail.com
 */
public class FlyNotBehavior implements FlyBehavior {
    /**
     * 不会飞
     */
    public void fly() {
        System.out.println("我不会飞行");
    }
}
