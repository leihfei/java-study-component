package com.lnlr.common.filter;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.lnlr.common.constains.SystemConstants;
import com.lnlr.common.jwt.Audience;
import com.lnlr.common.jwt.JwtUtils;
import com.lnlr.common.utils.ExcludePathUtils;
import com.lnlr.security.service.SysExcludePathService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author:leihfei
 * @description jwt验证拦截器
 * 必须把该拦截器的执行顺序设置为较早，必须在用到了ExcludePathUtils的前面
 * @date:Create in 11:06 2018/10/26
 * @email:leihfein@gmail.com
 */
@Slf4j
public class JwtVerificationFilter implements Filter {
    @Autowired
    private SysExcludePathService sysExcludePathService;

    @Autowired
    private Audience audience;

    /**
     * 初始化paths
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化Jwt拦截器,生成拦截器url");
        ExcludePathUtils.paths = sysExcludePathService.findAll();
    }

    /**
     * 处理流程：
     * 判断请求是否需要jwt登录验证
     * 是： 判断是否有jwt
     * 否： 直接放过
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("进入JwtFilter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Preconditions.checkNotNull(ExcludePathUtils.paths, "拦截器过滤地址异常!");
        String path = request.getRequestURI();
        // 检查是否存在放过拦截,返回false说明需要认证
        if (!ExcludePathUtils.checkExcludeUrl(servletRequest, servletResponse, path)) {
            // 表示需要验证jat,验证通过就放过，否则需要登录
            String header = request.getHeader(SystemConstants.AUTHORIZATION);
            if (StringUtils.isEmpty(header)) {
                log.warn("请求:{} 无Authorization信息", path);
                response.sendRedirect(SystemConstants.UNAUTH);
                return;
            }
            // 存在继续校验是否过期
            String token = header.substring(SystemConstants.TOKEN_TYPE.length());
            Claims claims = JwtUtils.parseJWT(token, audience.getBase64Secret());
            if (claims == null) {
                log.warn("请求:{} token信息异常", path);
                response.sendRedirect(SystemConstants.UNAUTH);
                return;
            }
            // 有token,并且是有效的token，检查时间是否过期，如果过期，那么就从新生成token
            if (ExcludePathUtils.checkExpire(request, response, claims)) return;
            if (claims.get(SystemConstants.JWT_LOGIN_USER) != null) {
                // token有效，并且能有用户数据
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }


    @Override
    public void destroy() {
        ExcludePathUtils.paths = Lists.newArrayList();
    }

}
