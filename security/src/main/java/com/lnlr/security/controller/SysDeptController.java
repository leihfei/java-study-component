package com.lnlr.security.controller;

import com.lnlr.security.pojo.master.dto.DeptParam;
import com.lnlr.security.pojo.master.dto.DeptLevelTree;
import com.lnlr.security.service.SysDeptService;
import com.lnlr.security.service.impl.SysTreeService;
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
    @ApiImplicitParam(dataTypeClass = DeptParam.class, paramType = "query")
    public Response create(@RequestBody DeptParam deptParam) {
        return deptService.create(deptParam);
    }


    @RequestMapping(value = "/tree.json",method = RequestMethod.GET)
    @ApiOperation(value = "获取部门树")
    public Response tree() {
        List<DeptLevelTree> deptLevelTrees = treeService.deptTree();
        return new ObjectResponse<>(deptLevelTrees);
    }

    @PostMapping(value = "/update.json")
    @ApiOperation(value = "更新部门")
    @ApiImplicitParam(dataTypeClass = DeptParam.class, paramType = "query")
    public Response update(@RequestBody DeptParam deptParam) {
        return deptService.update(deptParam);
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
