package com.lnlr.authority.security.controller;

import com.lnlr.authority.security.pojo.master.dto.UserDTO;
import com.lnlr.authority.security.service.SysUserService;
import com.lnlr.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:leihfei
 * @description 权限管理系统用户控制层
 * @date:Create in 19:49 2018/8/30
 * @email:leihfein@gmail.com
 */
@RestController
@RequestMapping(value = "/sys/user")
@Api(value = "系统用户", description = "系统用户管理类")
@Slf4j
public class SysUserController {

    @Autowired
    private SysUserService userService;


    @PostMapping(value = "/create.json")
    @ApiOperation(value = "新增用户信息")
    @ApiImplicitParam(dataTypeClass = UserDTO.class, paramType = "query")
    public Response create(@RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @PostMapping(value = "/update.json")
    @ApiOperation(value = "更新用户信息")
    @ApiImplicitParam(dataTypeClass = UserDTO.class, paramType = "query")
    public Response update(@RequestBody UserDTO userDTO) {
        return userService.update(userDTO);
    }


}
