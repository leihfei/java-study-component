package com.lnlr.design.template;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 12:46 2018/10/25
 * @email:leihfein@gmail.com
 */
public class CoffeTemplate extends TemplateMethod {
    public void draw() {
        System.out.println("咖啡添加主料");
    }

    public void addOther() {
        System.out.println("咖啡添加辅料");
    }
}
