package com.lnlr.config.filter;

import com.lnlr.common.filter.JwtVerificationFilter;
import com.lnlr.common.filter.LoginFilter;
import com.lnlr.common.filter.PermissionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author:leihfei
 * @description 过滤器配置文件
 * @date:Create in 11:10 2018/10/26
 * @email:leihfein@gmail.com
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean registrationJwtVerificationFilterBean() {
        return registrationBean(getJwtVerificationFilter(), "/*", 1);
    }

    private FilterRegistrationBean registrationBean(Filter filter, String pattern, int order) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.addUrlPatterns(pattern);
        registration.setOrder(order);
        return registration;
    }

    @Bean
    public FilterRegistrationBean registrationLoginFilterBean() {
        return registrationBean(getLoginFilter(), "/*", 2);
    }

    @Bean
    public FilterRegistrationBean registrationPermissionFilterBean() {
        return registrationBean(getPermissionFilter(), "/*", 3);
    }

    @Bean
    public LoginFilter getLoginFilter() {
        return new LoginFilter();
    }

    @Bean
    public PermissionFilter getPermissionFilter() {
        return new PermissionFilter();
    }

    @Bean
    public JwtVerificationFilter getJwtVerificationFilter() {
        return new JwtVerificationFilter();
    }
}
