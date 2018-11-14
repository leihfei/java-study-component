package com.lnlr.common.filter;

import com.alibaba.druid.support.json.JSONUtils;
import com.lnlr.common.constains.SystemConstants;
import com.lnlr.common.utils.ExcludePathUtils;
import com.lnlr.common.utils.JsonUtils;
import com.lnlr.common.utils.RequestHolder;
import com.lnlr.security.service.impl.SysCoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author:leihfei
 * @description 权限拦截Filter
 * 在进入该权限拦截Filter之前，已经经过了jwt,login的filter处理，那么
 * 在这里就不用考虑过滤的问题，只需要检查是否需要放过，不检查权限即可
 * @date:Create in 23:56 2018/11/7
 * @email:leihfein@gmail.com
 */
@Slf4j
public class PermissionFilter implements Filter {

    @Autowired
    private SysCoreService coreService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("进入权限拦截Filter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 检查是否存在放过拦截,返回false说明需要认证
        String path = request.getServletPath();
        if (!ExcludePathUtils.checkExcludeUrl(servletRequest, servletResponse, path)) {
            // 需要进行权限校验
            if (!coreService.hasPermission(path)) {
                Map<String, String[]> parameterMap = request.getParameterMap();
                log.warn("无权限访问,用户:{},访问:{},参数:{}", RequestHolder.currentUser().getRealName(), path, JsonUtils.object2Json(parameterMap));
                // 没有权限
                response.sendRedirect(SystemConstants.UNAUTHORIZED);
                return;
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
        return;
    }

    @Override
    public void destroy() {

    }
}
