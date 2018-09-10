package com.lnlr.design.decorator;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 21:11 2018/9/10
 * @email:leihfein@gmail.com
 */
public class HouseBlend extends Behaver {
    public HouseBlend(){
        description = "House Blend Coffed";
    }
    @Override
    public Double cost() {
        return 0.99;
    }
}
