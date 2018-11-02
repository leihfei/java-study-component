package com.lnlr.security.service.impl;

import com.lnlr.common.utils.RequestHolder;
import com.lnlr.security.pojo.master.entity.SysAcl;
import com.lnlr.security.pojo.master.entity.SysRoleAcl;
import com.lnlr.security.pojo.master.entity.SysUser;
import com.lnlr.security.pojo.master.entity.SysUserRole;
import com.lnlr.security.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author:leihfei
 * @description: 核心角色权限，用户角色业务实现
 * @date:Create in 0:46 2018/11/1
 * @email:leihfein@gmail.com
 */
@Service
@Slf4j
public class SysCoreService {

    @Autowired
    private SysAclService aclService;

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysUserRoleService userRoleService;

    @Autowired
    private SysRoleAclService roleAclService;


    /**
     * @param
     * @return java.util.List<com.lnlr.security.pojo.master.entity.SysAcl>
     * @author: leihfei
     * @description 获取登录用户权限列表
     * @date: 1:08 2018/11/1
     * @email: leihfein@gmail.com
     */
    public List<SysAcl> getCurrentUserAclList() {
        Integer id = RequestHolder.currentUser().getId();
        return getUserAclList(id);
    }

    /**
     * @param roleId 角色Id
     * @return java.util.List<com.lnlr.security.pojo.master.entity.SysAcl>
     * @author: leihfei
     * @description 通过角色Id获取所有权限点列表
     * @date: 1:09 2018/11/1
     * @email: leihfein@gmail.com
     */
    public List<SysAcl> getRoleAclList(Integer roleId) {
        List<SysRoleAcl> roleAcls = roleAclService.findAllByRoleIdList(Lists.newArrayList(roleId));
        if (CollectionUtils.isNotEmpty(roleAcls)) {
            return Lists.newArrayList();
        }
        List<Integer> aclIds = roleAcls.stream().map(e -> e.getAclId()).collect(Collectors.toList());
        return aclService.findAllByIds(aclIds);
    }

    /**
     * @param id 用户Id
     * @return java.util.List<com.lnlr.security.pojo.master.entity.SysAcl>
     * @author: leihfei
     * @description 获取当前登录用户的所有权限点
     * @date: 1:08 2018/11/1
     * @email: leihfein@gmail.com
     */
    private List<SysAcl> getUserAclList(Integer id) {
        if (isSuperAdmin()) {
            return aclService.findAll();
        }
        // 查询单独的权限
        List<SysUserRole> roleList = userRoleService.findAllRoleListByUserId(id);
        if (CollectionUtils.isNotEmpty(roleList)) {
            return Lists.newArrayList();
        }
        List<Integer> userRoleIdList = roleList.stream().map(e -> e.getRoleId()).collect(Collectors.toList());
        List<SysRoleAcl> roleAcls = roleAclService.findAllByRoleIdList(userRoleIdList);
        if (CollectionUtils.isNotEmpty(roleAcls)) {
            return Lists.newArrayList();
        }
        List<Integer> aclIds = roleAcls.stream().map(e -> e.getAclId()).collect(Collectors.toList());
        // 返回所有的权限
        return aclService.findAllByIds(aclIds);
    }

    /**
     * @param
     * @return java.util.List<com.lnlr.security.pojo.master.entity.SysAcl>
     * @author: leihfei
     * @description 获取所有的系统权限点
     * @date: 1:15 2018/11/1
     * @email: leihfein@gmail.com
     */
    public List<SysAcl> getAclAll() {
        return aclService.findAll();
    }

    private boolean isSuperAdmin() {
        // 这里是我自己定义了一个假的超级管理员规则，实际中要根据项目进行修改
        // 可以是配置文件获取，可以指定某个用户，也可以指定某个角色
        SysUser sysUser = RequestHolder.currentUser();
        if (sysUser.getEmail().contains("admin")) {
            return true;
        }
        return false;
    }
}
