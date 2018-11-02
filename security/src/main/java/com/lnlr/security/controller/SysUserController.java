package com.lnlr.security.controller;

import com.google.common.collect.Maps;
import com.lnlr.common.constains.SystemConstants;
import com.lnlr.common.entity.IdEntity;
import com.lnlr.common.jpa.model.NgPager;
import com.lnlr.common.response.FailedResponse;
import com.lnlr.common.response.ObjectResponse;
import com.lnlr.common.response.Response;
import com.lnlr.security.pojo.master.dto.CheckPassParam;
import com.lnlr.security.pojo.master.dto.UserParam;
import com.lnlr.security.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

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
    @ApiImplicitParam(dataTypeClass = UserParam.class, paramType = "query")
    public Response create(@RequestBody UserParam userParam) {
        return userService.create(userParam);
    }

    @PostMapping(value = "/update.json")
    @ApiOperation(value = "更新用户信息")
    @ApiImplicitParam(dataTypeClass = UserParam.class, paramType = "query")
    public Response update(@RequestBody UserParam userParam) {
        return userService.update(userParam);
    }

    @PostMapping(value = "/page.json")
    @ApiOperation(value = "分页查询用户")
    @ApiImplicitParam(dataTypeClass = NgPager.class, paramType = "query")
    public Response page(@RequestBody NgPager ngPager) {
        return new ObjectResponse<>(userService.page(ngPager));
    }

    @PostMapping(value = "/checkPass.json")
    @ApiOperation(value = "检查用户旧密码")
    @ApiImplicitParam(dataTypeClass = CheckPassParam.class, paramType = "query")
    public Response checkPass(@RequestBody CheckPassParam checkPassParam) {
        return new ObjectResponse<>(userService.checkPass(checkPassParam));
    }

    @PostMapping(value = "/updatePass.json")
    @ApiOperation(value = "更新密码")
    @ApiImplicitParam(dataTypeClass = CheckPassParam.class, paramType = "query")
    public Response updatePass(@RequestBody CheckPassParam checkPassParam) {
        if (userService.updatePass(checkPassParam) == true) {
            return new ObjectResponse<>("密码更新成功!");
        }
        return new FailedResponse<>("密码更新失败!");
    }


    @PostMapping("/acls.json")
    @ApiOperation(value = "查询用户角色数据")
    @ApiImplicitParam(dataTypeClass = IdEntity.class, paramType = "query")
    public Response acls(@RequestBody IdEntity idEntity) {
        Map<String, Object> map = Maps.newHashMap();
//        map.put("acls", sysTreeService.userAclTree(userId));
//        map.put("roles", sysRoleService.getRoleListByUserId(userId));

        return new ObjectResponse<>(map);
    }

    @GetMapping("/test.json")
    @ApiOperation(value = "测试重定向")
    @ApiImplicitParam(dataTypeClass = IdEntity.class, paramType = "query")
    public Response test(HttpServletResponse response) throws IOException {
        response.sendRedirect(SystemConstants.UNAUTH);
        return new ObjectResponse<>(null);
    }


}
