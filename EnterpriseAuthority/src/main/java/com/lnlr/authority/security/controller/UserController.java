package com.lnlr.authority.security.controller;

import com.lnlr.authority.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:leihfei
 * @description 权限管理系统用户控制层
 * @date:Create in 19:49 2018/8/30
 * @email:leihfein@gmail.com
 */
@RestController
@RequestMapping(value = "/sysUser")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "findAll")
    public Object findAll() {
        return userService.findAll();
    }
}
