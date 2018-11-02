package com.lnlr.config.filter;

import com.lnlr.common.filter.JwtVerificationFilter;
import com.lnlr.common.filter.LoginFilter;
import io.jsonwebtoken.Jwt;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(getJwtVerificationFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }


    @Bean
    public FilterRegistrationBean registrationLoginFilterBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(getLoginFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(2);
        return registration;
    }

    @Bean
    public LoginFilter getLoginFilter() {
        return new LoginFilter();
    }

    @Bean
    public JwtVerificationFilter getJwtVerificationFilter() {
        return new JwtVerificationFilter();
    }
}
