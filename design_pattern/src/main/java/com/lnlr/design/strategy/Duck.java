package com.lnlr.design.strategy;

import com.lnlr.design.strategy.intface.FlyBehavior;
import com.lnlr.design.strategy.intface.QuackBehavior;

/**
 * @author:leihfei
 * @description 一个鸭子抽象类
 * @date:Create in 17:50 2018/9/9
 * @email:leihfein@gmail.com
 */
public abstract class Duck {
     FlyBehavior flyBehavior;

    QuackBehavior quackBehaver;

    public abstract void display();

    public void performFly(){
        flyBehavior.fly();
    }

    public void performQuack(){
        quackBehaver.quack();
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehaver(QuackBehavior quackBehaver) {
        this.quackBehaver = quackBehaver;
    }
}
