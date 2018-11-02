package com.lnlr.design.proxy.staticP;

/**
 * @author:leihfei
 * @description: 静态代理
 * @date:Create in 23:54 2018/10/30
 * @email:leihfein@gmail.com
 */
public class StaticTest {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        userDao.save();
    }
}

interface  UserDao{
    void save();
}

class UserDaoImpl implements  UserDao{

    public void save() {
        System.out.println("静态代理");
        System.out.println("执行save方法");
        System.out.println("save方法结束，动态代理结束");
    }
}
