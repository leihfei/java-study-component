package com.lnlr.design.strategy;

import com.lnlr.design.strategy.intface.FlyBehavior;
import com.lnlr.design.strategy.intface.QuackBehavior;

/**
 * @author:leihfei
 * @description 主要测试类
 * @date:Create in 17:58 2018/9/9
 * @email:leihfein@gmail.com
 */
public class Main {
    public static void main(String[] args) {
        GreenDuck greenDuck = new GreenDuck();
        FlyBehavior flyBehavior = new FlyWithBehavior();
        QuackBehavior quackBehavior = new GaGaBehavior();
        greenDuck.setFlyBehavior(flyBehavior);
        greenDuck.setQuackBehaver(quackBehavior);

        greenDuck.display();
        greenDuck.performFly();
        greenDuck.performQuack();
    }
}
