package com.lnlr.design.strategy;

import com.lnlr.design.strategy.intface.FlyBehavior;

/**
 * @author:leihfei
 * @description 定义一种会飞的能力
 * @date:Create in 17:53 2018/9/9
 * @email:leihfein@gmail.com
 */
public class FlyWithBehavior implements FlyBehavior {

    /**
     * 会飞的能力
     */
    public void fly() {
        System.out.println("我能飞行");
    }
}
