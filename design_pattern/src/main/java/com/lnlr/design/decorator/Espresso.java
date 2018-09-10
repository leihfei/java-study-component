package com.lnlr.design.decorator;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 21:09 2018/9/10
 * @email:leihfein@gmail.com
 */
public class Espresso extends Behaver {
    public Espresso(){
        description = "Espresso";
    }
    public Double cost(){
        return 1.99;
    }

}
