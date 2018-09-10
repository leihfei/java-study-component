package com.lnlr.design.decorator;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 21:16 2018/9/10
 * @email:leihfein@gmail.com
 */
public class Main {
    public static void main(String[] args) {
        Behaver behaver = new Espresso();
        System.out.println(behaver.getDescription() + " s " + behaver.cost());

        behaver = new Mocha(behaver);
        behaver = new Mocha(behaver);
        System.out.println(behaver.getDescription() + " s " + behaver.cost());

    }
}
