package com.lnlr.design.proxy.dynic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 23:57 2018/10/30
 * @email:leihfein@gmail.com
 */
public class ProxyFactory {
    private Object target;

    ProxyFactory() {
    }

    ProxyFactory(Object target) {
        this.target = target;
    }

    Object getInstances() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("动态代理");
                Object invoke = method.invoke(target, args);
                System.out.println("结束");
                return invoke;
            }
        });
    }
}

class Test {
    public static void main(String[] args) {
        UserDao userDao = new IUserDao();
        UserDao instances = (UserDao) new ProxyFactory(userDao).getInstances();
        instances.save();
    }
}
