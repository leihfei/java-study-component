package com.lnlr.design.strategy;

import com.lnlr.design.strategy.intface.QuackBehavior;

/**
 * @author:leihfei
 * @description 嘎嘎叫
 * @date:Create in 18:00 2018/9/9
 * @email:leihfein@gmail.com
 */
public class GaGaBehavior implements QuackBehavior {
    public void quack() {
        System.out.println("格格叫");
    }
}
