package com.lnlr.security.controller;

import com.lnlr.common.entity.IdEntity;
import com.lnlr.common.jpa.model.NgPager;
import com.lnlr.common.response.ObjectResponse;
import com.lnlr.common.response.Response;
import com.lnlr.common.response.SuccessResponse;
import com.lnlr.security.pojo.master.dto.AclModuleParam;
import com.lnlr.security.pojo.master.dto.AclParam;
import com.lnlr.security.pojo.master.dto.RoleParam;
import com.lnlr.security.service.SysRoleService;
import com.lnlr.security.service.impl.SysTreeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author:leihfei
 * @description: 系统角色控制器
 * @date:Create in 16:24 2018/10/30
 * @email:leihfein@gmail.com
 */
@RestController
@RequestMapping(value = "/sys/role")
@Api(value = "角色管理", description = "权限管理系统角色管理")
@Slf4j
public class SysRoleController {

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysTreeService treeService;


    @PostMapping(value = "/create.json")
    @ApiOperation(value = "新增角色")
    @ApiImplicitParam(dataTypeClass = AclParam.class, paramType = "query")
    public Response create(@RequestBody RoleParam roleParam) {
        return roleService.create(roleParam);
    }


    @PostMapping(value = "/update.json")
    @ApiOperation(value = "更新角色")
    @ApiImplicitParam(dataTypeClass = AclParam.class, paramType = "query")
    public Response update(@RequestBody RoleParam roleParam) {
        return roleService.update(roleParam);
    }


    @PostMapping(value = "/page.json")
    @ApiOperation(value = "分页查询角色")
    @ApiImplicitParam(dataTypeClass = NgPager.class, paramType = "query")
    public Response page(@RequestBody NgPager ngPager) {
        return new ObjectResponse<>(roleService.page(ngPager));
    }


    @PostMapping(value = "/delete.json")
    @ApiOperation(value = "删除角色")
    @ApiImplicitParam(dataTypeClass = IdEntity.class, paramType = "query")
    public Response delete(@RequestBody IdEntity idEntity) {
        roleService.delete(idEntity);
        return new SuccessResponse("删除成功");
    }

    @PostMapping(value = "/list.json")
    @ApiOperation(value = "查询角色")
    public Response list() {
        return new ObjectResponse<>(roleService.list());
    }


    @PostMapping(value = "/roleTree.json")
    @ApiOperation(value = "获取模块权限树")
    @ApiImplicitParam(dataTypeClass = IdEntity.class, paramType = "query")
    public Response roleTree(@RequestBody IdEntity idEntity) {
        return new ObjectResponse<>(treeService.roleTree(idEntity));
    }
}
