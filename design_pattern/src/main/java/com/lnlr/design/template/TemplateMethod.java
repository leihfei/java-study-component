package com.lnlr.design.template;

/**
 * @author:leihfei
 * @description: 模板模式超类
 * @date:Create in 12:42 2018/10/25
 * @email:leihfein@gmail.com
 */
public abstract class TemplateMethod {

    public void prepard() {
        oid();
        draw();
        inTo();
        addOther();
    }

    /**
     * 烧水
     */
    public final void oid() {
        System.out.println("烧水");
    }

    /**
     * 加水
     */
    public final void inTo() {
        System.out.println("把水倒入杯子");
    }

    /**
     * 添加主料
     */
    public abstract void draw();

    /**
     * 添加作料
     */
    public abstract void addOther();
}
