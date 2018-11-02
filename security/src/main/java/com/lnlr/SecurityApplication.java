package com.lnlr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author leihf
 * @desc 企业级权限管理系统
 * 系统设计：
 *  项目启动，添加数据库中配置的默认放过url,
 *  需要登录验证的url,第一个拦截器走是否登录拦截器，没有登录，跳转到登录，有登录
 *  继续走权限验证拦截器，没有权限，返回没有权限，有权限继续访问。
 */
@SpringBootApplication
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

}
