package com.lnlr.common.utils;

import com.google.common.collect.Lists;
import com.lnlr.common.constains.SystemConstants;
import com.lnlr.security.pojo.master.entity.SysDefaultFilter;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author:leihfei
 * @description: 排除url需要认证工具类
 * @date:Create in 14:58 2018/10/26
 * @email:leihfein@gmail.com
 */
@Slf4j
public class ExcludePathUtils {
    /**
     * 定义一个数组
     */
    public static List<SysDefaultFilter> paths = Lists.newArrayList();


    /**
     * 是否过期时间,需要转为毫秒
     * 最后5分钟就需要进行重新生成token
     */
    private static long expireTime = 5 * 60 * 1000;


    /**
     * @param servletRequest,
     * @param servletResponse,
     * @param path             请求路径
     * @return boolean 如果url为过滤，那么就放过，否则返回false,需要校验token
     * @author: leihfei
     * @description
     * @date: 14:49 2018/10/26
     * @email: leihfein@gmail.com
     */
    public static boolean checkExcludeUrl(ServletRequest servletRequest, ServletResponse servletResponse, String path) throws IOException, ServletException {
        for (int i = 0; i < ExcludePathUtils.paths.size(); i++) {
            String url = ExcludePathUtils.paths.get(i).getUrl();
            // 判断是否有**号
            int i1 = url.indexOf("*");
            if (i1 != -1) {
                url = url.substring(0, i1);
            }
            if (path.contains(url)) {
                // 找到了相同的地址，说明这是需要放过的。
                log.info("该请求:{} 不需要验证", path);
                return true;
            }
        }
        return false;
    }

    /**
     * @param request  请求,
     * @param response 响应,
     * @param claims   token数据
     * @return boolean
     * @author: leihfei
     * @description 过期，就跳转到生成，没过期就返回false
     * @date: 14:47 2018/10/26
     * @email: leihfein@gmail.com
     */
    public static boolean checkExpire(HttpServletRequest request, HttpServletResponse response, Claims claims) throws IOException {
        Date expiration = claims.getExpiration();
        Date currentDate = new Date();
        String path = request.getRequestURI();
        if (expiration.getTime() - currentDate.getTime() <= expireTime) {
            //跳转到特定接口进行token再次生成
            String userId = (String) claims.get(SystemConstants.JWT_LOGIN_USER);
            String userName = (String) claims.get(SystemConstants.JWT_LOGIN_USERNAME);
            request.setAttribute(SystemConstants.JWT_LOGIN_USER, userId);
            request.setAttribute(SystemConstants.JWT_LOGIN_USERNAME, userName);
            log.info("请求: {} 以过期，需要重新生成token信息,信息数据:userId={},userName={}", path,userId,userName);
            response.sendRedirect(SystemConstants.CREATE_TOKEN);
            return true;
        }
        return false;
    }
}
