package com.lnlr.security.controller;

import com.lnlr.security.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:leihfei
 * @description 用户控制器
 * @date:Create in 11:22 2018/9/8
 * @email:leihfein@gmail.com
 */
@RestController
@RequestMapping(value = "user")
@Api(value = "用户控制器", description = "用户管理")
public class UserController {

    @Autowired
    private SysUserService userService;

}
