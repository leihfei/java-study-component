package com.lnlr.common.interceptor;

import com.lnlr.common.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author:leihfei
 * @description http启动监听器，拦截器
 * @date:Create in 1:16 2018/9/3
 * @email:leihfein@gmail.com
 */
@Slf4j
@Component
public class HttpInterceptor extends HandlerInterceptorAdapter {

    private final static String CURRENT_TIME = "current_time";

    /**
     * 请求过来之前处理
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        Map<String, String[]> parameterMap = request.getParameterMap();
        log.info("{rquest start  url:{},params:{}", url, JsonUtils.object2Json(parameterMap));
        request.setAttribute(CURRENT_TIME, System.currentTimeMillis());
        return true;
    }

    /**
     * 请求正常结束之后调用
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        String url = request.getRequestURL().toString();
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        log.info("rquest finish {url:{},params:{}", url, JsonUtils.object2Json(parameterMap));
    }

    /**
     * 请求正常结束之后调用
     * 异常情况下也会调用
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = request.getRequestURL().toString();
        long time = (Long) request.getAttribute(CURRENT_TIME);
        log.info("当前请求url:{},花费:{}", url, System.currentTimeMillis() - time);

    }
}
