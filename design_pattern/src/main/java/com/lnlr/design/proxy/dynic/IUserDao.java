package com.lnlr.design.proxy.dynic;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 0:07 2018/10/31
 * @email:leihfein@gmail.com
 */
public class IUserDao implements UserDao {

    @Override
    public void save() {
        System.out.println("保存.....");
    }
}
