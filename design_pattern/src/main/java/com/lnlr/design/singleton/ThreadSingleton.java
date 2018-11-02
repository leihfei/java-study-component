package com.lnlr.design.singleton;

/**
 * @author:leihfei
 * @description 多线程下的单例模式
 * @date:Create in 23:24 2018/10/23
 * @email:leihfein@gmail.com
 */
public class ThreadSingleton {

    private static ThreadSingleton instance = null;

    private ThreadSingleton(){}

    public static synchronized ThreadSingleton getInstance(){
        if(instance == null){
            instance = new ThreadSingleton();
        }
        return instance;
    }
}
