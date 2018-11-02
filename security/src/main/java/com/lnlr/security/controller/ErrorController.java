package com.lnlr.security.controller;

import com.lnlr.common.response.FailedResponse;
import com.lnlr.common.response.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.lnlr.common.response.Response.NO_LOGIN;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 10:43 2018/10/10
 * @email:leihfein@gmail.com
 */
@RestController
public class ErrorController {

    @PostMapping(value = "/error")
    public Response error() {
        return new FailedResponse<>(NO_LOGIN, "发生未知错误，请联系管理员!");
    }
}
