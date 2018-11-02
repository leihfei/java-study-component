package com.lnlr.common.constains;

import org.springframework.stereotype.Component;

/**
 * @author:leihfei
 * @description 系统常量
 * @date:Create in 16:12 2018/9/14
 * @email:leihfein@gmail.com
 */
@Component
public class SystemConstants {

    private static String contextPath = "/security";
    /**
     * jwt 登录用户
     */
    public static final String JWT_LOGIN_USER = "loginUser";
    /**
     * jwt登录用户唯一编号
     */
    public static final String JWT_LOGIN_USERNAME = "unique_name";
    /**
     * TOKEN_TYPE
     */
    public static final String TOKEN_TYPE = "bearer ";

    /**
     * authorizaiton
     */
    public static final String AUTHORIZATION = "Authorization";


    /**
     * jessionId后缀
     */
    public static final String JESSIONID = "_JESSIONID";

    /**
     * 登陆地址
     */
    public static final String LOGIN = contextPath + "/sys/authorizing/login";


    /**
     * 未登录提示
     */
    public static final String UNAUTH = contextPath + "/sys/authorizing/unauth";

    /**
     * 提示未授权页面
     */
    public static final String UNAUTHORIZED = contextPath + "/sys/authorizing/unauthorized";

    /**
     * 跳转到token生成页面
     */
    public static final String CREATE_TOKEN = contextPath + "/sys/authorizing/create_tokem";


    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWORD = "123456";
}
