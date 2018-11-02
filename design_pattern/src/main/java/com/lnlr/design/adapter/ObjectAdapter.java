package com.lnlr.design.adapter;

/**
 * @author:leihfei
 * @description: 对象适配器
 * @date:Create in 23:09 2018/10/24
 * @email:leihfein@gmail.com
 */
public class ObjectAdapter implements Durk{

    private Thurk thurk;

    public ObjectAdapter(){}
    public ObjectAdapter(Thurk thurk){
        System.out.print("适配器....");
        this.thurk = thurk;
    }

    public void sign() {
        thurk.sign();
    }

    public void flay() {
        System.out.print("适配器....");
        thurk.flay();
    }
}
