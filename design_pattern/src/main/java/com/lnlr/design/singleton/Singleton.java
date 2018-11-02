package com.lnlr.design.singleton;

/**
 * @author:leihfei
 * @description 单例模式
 * @date:Create in 23:17 2018/10/23
 * @email:leihfein@gmail.com
 */
public class Singleton {
    private static Singleton instance = new Singleton();
    private Singleton(){}

    public static Singleton getInstance(){
        return instance;
    }
}
