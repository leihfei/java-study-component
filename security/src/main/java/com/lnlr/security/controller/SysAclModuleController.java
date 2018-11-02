package com.lnlr.security.controller;

import com.lnlr.common.entity.IdEntity;
import com.lnlr.common.jpa.model.NgPager;
import com.lnlr.common.response.ObjectResponse;
import com.lnlr.common.response.Response;
import com.lnlr.security.pojo.master.dto.AclModuleParam;
import com.lnlr.security.pojo.master.dto.ModuleLevelTree;
import com.lnlr.security.service.SysAclModuleService;
import com.lnlr.security.service.impl.SysTreeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author:leihfei
 * @description: 权限点和模板controller
 * @date:Create in 16:20 2018/10/26
 * @email:leihfein@gmail.com
 */
@RestController
@RequestMapping(value = "/sys/aclModule")
@Api(value = "权限模块管理", description = "权限模块管理")
@Slf4j
public class SysAclModuleController {

    @Autowired
    private SysAclModuleService aclModuleService;

    @Autowired
    private SysTreeService sysTreeService;


    @PostMapping(value = "/create.json")
    @ApiOperation(value = "新增权限模块")
    @ApiImplicitParam(dataTypeClass = AclModuleParam.class, paramType = "query")
    public Response create(@RequestBody AclModuleParam aclModuleParam) {
        return aclModuleService.create(aclModuleParam);
    }

    @GetMapping(value = "/tree.json")
    @ApiOperation(value = "获取权限模块树")
    public Response tree() {
        List<ModuleLevelTree> moduleLevelTrees = sysTreeService.moduleTree();
        return new ObjectResponse<>(moduleLevelTrees);
    }


    @PostMapping(value = "/update.json")
    @ApiOperation(value = "更新权限模块")
    @ApiImplicitParam(dataTypeClass = AclModuleParam.class, paramType = "query")
    public Response update(@RequestBody AclModuleParam aclModuleParam) {
        return aclModuleService.update(aclModuleParam);
    }

    @PostMapping(value = "/delete.json")
    @ApiOperation(value = "删除权限模块")
    @ApiImplicitParam(dataTypeClass = AclModuleParam.class, paramType = "query")
    public Response delete(@RequestBody IdEntity idEntity) {
        return aclModuleService.delete(idEntity);
    }


    @PostMapping(value = "/page.json")
    @ApiOperation(value = "分页查询权限模块")
    @ApiImplicitParam(dataTypeClass = NgPager.class, paramType = "query")
    public Response page(@RequestBody NgPager ngPager) {
        return new ObjectResponse<>(aclModuleService.page(ngPager));
    }


}
