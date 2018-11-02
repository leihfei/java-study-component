package com.lnlr.design.template;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 12:46 2018/10/25
 * @email:leihfein@gmail.com
 */
public class Test {
    public static void main(String[] args) {
        TemplateMethod templateMethod = new CoffeTemplate();
        templateMethod.prepard();

        templateMethod = new TeaTemplate();
        templateMethod.prepard();
    }
}
