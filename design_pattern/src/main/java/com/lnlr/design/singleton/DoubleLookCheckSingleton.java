package com.lnlr.design.singleton;

/**
 * @author:leihfei
 * @description 双重检查加锁的方式
 * @date:Create in 23:31 2018/10/23
 * @email:leihfein@gmail.com
 */
public class DoubleLookCheckSingleton {

    private volatile static DoubleLookCheckSingleton instance = null;

    private DoubleLookCheckSingleton(){}

    /**
     * 双重检查枷锁
     * @return
     */
    public static DoubleLookCheckSingleton getInstance(){
        if (instance == null) {
            synchronized (DoubleLookCheckSingleton.class){
                if(instance == null){
                    instance = new DoubleLookCheckSingleton();
                }
            }
        }
        return instance;
    }
}
