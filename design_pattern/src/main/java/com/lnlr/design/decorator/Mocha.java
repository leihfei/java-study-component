package com.lnlr.design.decorator;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 21:15 2018/9/10
 * @email:leihfein@gmail.com
 */
public class Mocha extends CondimentDecorator {
    Behaver behaver;

    public Mocha(){}
    public Mocha(Behaver behaver){
        this.behaver = behaver;
    }

    @Override
    public String getDescription() {
        return behaver.getDescription() + " , Mocha";
    }

    @Override
    public Double cost() {
        return behaver.cost() + 0.22;
    }
}
