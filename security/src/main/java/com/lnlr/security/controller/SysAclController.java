package com.lnlr.security.controller;

import com.lnlr.common.entity.IdEntity;
import com.lnlr.common.jpa.model.NgPager;
import com.lnlr.common.response.ObjectResponse;
import com.lnlr.common.response.Response;
import com.lnlr.common.response.SuccessResponse;
import com.lnlr.security.pojo.master.dto.AclModuleParam;
import com.lnlr.security.pojo.master.dto.AclParam;
import com.lnlr.security.service.SysAclService;
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
 * @description: 权限点控制器
 * @date:Create in 16:20 2018/10/26
 * @email:leihfein@gmail.com
 */
@RestController
@RequestMapping(value = "/sys/acl")
@Api(value = "权限管理", description = "权限点管理")
@Slf4j
public class SysAclController {

    @Autowired
    private SysAclService aclService;


    @PostMapping(value = "/create.json")
    @ApiOperation(value = "新增权限点")
    @ApiImplicitParam(dataTypeClass = AclParam.class, paramType = "query")
    public Response create(@RequestBody AclParam aclParam) {
        return aclService.create(aclParam);
    }


    @PostMapping(value = "/update.json")
    @ApiOperation(value = "更新权限点")
    @ApiImplicitParam(dataTypeClass = AclParam.class, paramType = "query")
    public Response update(@RequestBody AclParam aclParam) {
        return aclService.update(aclParam);
    }


    @PostMapping(value = "/page.json")
    @ApiOperation(value = "分页查询权限点")
    @ApiImplicitParam(dataTypeClass = NgPager.class, paramType = "query")
    public Response page(@RequestBody NgPager ngPager) {
        return new ObjectResponse<>(aclService.page(ngPager));
    }


    @PostMapping(value = "/delete.json")
    @ApiOperation(value = "删除权限点")
    @ApiImplicitParam(dataTypeClass = AclModuleParam.class, paramType = "query")
    public Response delete(@RequestBody IdEntity idEntity) {
        aclService.delete(idEntity);
        return new SuccessResponse("删除成功");
    }


}
