package com.lnlr.authority.security.controller;

import com.lnlr.authority.security.pojo.master.dto.UserDTO;
import com.lnlr.authority.security.service.SysUserService;
import com.lnlr.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@Api(value = "用户控制器",description = "用户管理")
public class UserController {

    @Autowired
    private SysUserService userService;

    @PostMapping(value = "/login.json")
    @ApiOperation(value = "登陆")
    @ApiImplicitParam(dataTypeClass = UserDTO.class, paramType = "query")
    public Response login(@RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }
}
