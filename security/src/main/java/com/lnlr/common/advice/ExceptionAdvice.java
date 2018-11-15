package com.lnlr.common.advice;

import com.lnlr.common.exception.ParamException;
import com.lnlr.common.exception.PermissionException;
import com.lnlr.common.response.FailedResponse;
import com.lnlr.common.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.lnlr.common.response.Response.NO_LOGIN;
import static com.lnlr.common.response.Response.NO_PRESSION;

/**
 * @author:leihfei
 * @description 全局异常捕获
 * @date:Create in 14:18 2018/8/31
 * @email:leihfein@gmail.com
 */
@ControllerAdvice
@Slf4j
public class ExceptionAdvice {
    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Response errorHandler(Exception ex) {
        if (ex == null) {
            return new FailedResponse<>("出现异常，但是异常未知!");
        }
        log.error("全局处理异常", ex);
        return new FailedResponse<>(ex.getMessage());
    }

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = NullPointerException.class)
    public Response errorHandler(NullPointerException ex) {
        if (ex == null) {
            return new FailedResponse<>("出现异常，但是异常未知!");
        }
        log.error("全局处理异常，出现空指针异常", ex);
        ex.printStackTrace();
        return new FailedResponse<>(ex.getMessage());
    }

    /**
     * 拦截捕捉自定义异常 ,权限异常
     * PermissionException.class
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = PermissionException.class)
    public Response myErrorHandler(PermissionException ex) {
        if (ex == null) {
            return new FailedResponse<>("出现异常，但是异常未知!");
        }
        ex.printStackTrace();
        log.error("全局异常处理：权限异常", ex);
        return new FailedResponse<>(ex.getMessage());
    }

    /**
     * 请求参数异常
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ParamException.class)
    public Response ParamException(ParamException ex) {
        if (ex == null) {
            return new FailedResponse<>("出现异常，但是异常未知!");
        }
        ex.printStackTrace();
        log.error("全局异常处理：请求参数异常", ex);
        return new FailedResponse<>(ex.getMessage());
    }

}
