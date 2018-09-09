package com.lnlr.authority.security.controller;

import com.lnlr.authority.security.pojo.master.dto.DeptDTO;
import com.lnlr.authority.security.pojo.master.dto.DeptLevelTree;
import com.lnlr.authority.security.service.SysDeptService;
import com.lnlr.authority.security.service.impl.SysTreeService;
import com.lnlr.common.entity.IdEntity;
import com.lnlr.common.jpa.model.NgPager;
import com.lnlr.common.response.ObjectResponse;
import com.lnlr.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author:leihfei
 * @description 权限管理系统部门控制器
 * @date:Create in 22:19 2018/9/3
 * @email:leihfein@gmail.com
 */
@RestController
@RequestMapping(value = "/sys/dept")
@Api(value = "部门管理", description = "权限管理系统部门管理")
@Slf4j
public class SysDeptController {

    /**
     * 部门管理service
     */
    @Autowired
    private SysDeptService deptService;

    /**
     * 部门树处理
     */
    @Autowired
    private SysTreeService treeService;


    @PostMapping(value = "/create.json")
    @ApiOperation(value = "新增部门")
    @ApiImplicitParam(dataTypeClass = DeptDTO.class, paramType = "query")
    public Response create(@RequestBody DeptDTO deptDTO) {
        return deptService.create(deptDTO);
    }


    @GetMapping(value = "/tree.json")
    @ApiOperation(value = "获取部门树")
    public Response tree() {
        List<DeptLevelTree> deptLevelTrees = treeService.deptTree();
        return new ObjectResponse<>(deptLevelTrees);
    }

    @PostMapping(value = "/update.json")
    @ApiOperation(value = "更新部门")
    @ApiImplicitParam(dataTypeClass = DeptDTO.class, paramType = "query")
    public Response update(@RequestBody DeptDTO deptDTO) {
        return deptService.update(deptDTO);
    }

    @PostMapping(value = "/page.json")
    @ApiOperation(value = "分页查询部门数据")
    @ApiImplicitParam(dataTypeClass = NgPager.class, paramType = "query")
    public Response page(@RequestBody NgPager page) {
        return new ObjectResponse<>(deptService.page(page));
    }

    @PostMapping(value = "/delete.json")
    @ApiOperation(value = "删除部门")
    @ApiImplicitParam(dataTypeClass = IdEntity.class, paramType = "query")
    public Response delete(@RequestBody IdEntity id) {
        return deptService.delete(id);
    }

}
