package com.lnlr.security.service.impl;

import com.lnlr.common.constains.CacheConstants;
import com.lnlr.common.utils.JsonUtils;
import com.lnlr.common.utils.RedisUtil;
import com.lnlr.common.utils.RequestHolder;
import com.lnlr.security.pojo.master.entity.SysAcl;
import com.lnlr.security.pojo.master.entity.SysRoleAcl;
import com.lnlr.security.pojo.master.entity.SysUser;
import com.lnlr.security.pojo.master.entity.SysUserRole;
import com.lnlr.security.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
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
    private SysUserRoleService userRoleService;

    @Autowired
    private SysRoleAclService roleAclService;

    @Autowired
    private RedisUtil redisUtil;

    private Integer expire = 600;

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
     * @param
     * @return java.util.List<com.lnlr.security.pojo.master.entity.SysAcl>
     * @author: leihfei
     * @description 获取登录用户权限列表, 通过缓存
     * @date: 1:08 2018/11/1
     * @email: leihfein@gmail.com
     */
    public List<SysAcl> getCurrentUserAclCacheList() {
        Integer id = RequestHolder.currentUser().getId();
        String cacheValue = redisUtil.get(redisUtil.generateCacheKey(CacheConstants.USER_ALCE, String.valueOf(id)));
        if (StringUtils.isBlank(cacheValue)) {
            // 没有缓存数据
            List<SysAcl> currentUserAclList = getCurrentUserAclList();
            if (CollectionUtils.isNotEmpty(currentUserAclList)) {
                redisUtil.set(redisUtil.generateCacheKey(CacheConstants.USER_ALCE, String.valueOf(id)), JsonUtils.object2Json(currentUserAclList), expire);
            }
            return currentUserAclList;
        }
        return JsonUtils.json2List(cacheValue,SysAcl.class);
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
        if (CollectionUtils.isEmpty(roleList)) {
            return Lists.newArrayList();
        }
        List<Integer> userRoleIdList = roleList.stream().map(e -> e.getRoleId()).collect(Collectors.toList());
        List<SysRoleAcl> roleAcls = roleAclService.findAllByRoleIdList(userRoleIdList);
        if (CollectionUtils.isEmpty(roleAcls)) {
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


    /**
     * @param path 接口地址
     * @return boolean
     * @author: leihfei
     * @description 校验是否拥有权限
     * @date: 0:01 2018/11/8
     * @email: leihfein@gmail.com
     */
    public boolean hasPermission(String path) {
        if (isSuperAdmin()) {
            return true;
        }
        // 在权限表中查询接口
        List<SysAcl> aclList = aclService.findAllByUrl(path);
        if (CollectionUtils.isEmpty(aclList)) {
            // 没找到直接没权限
            return false;
        }
        // 获得当前用户权限列表
        List<SysAcl> userAclList = getCurrentUserAclCacheList();
        Set<Integer> userAclIdSet = userAclList.stream().map(acl -> acl.getId()).collect(Collectors.toSet());
        boolean hasValidAcl = false;
        // 规则：只要有一个权限点有权限，那么我们就认为有访问权限
        for (SysAcl acl : aclList) {
            // 判断一个用户是否具有某个权限点的访问权限
            if (acl == null || acl.getStatus() != 1) {
                // 权限点无效
                continue;
            }
            hasValidAcl = true;
            if (userAclIdSet.contains(acl.getId())) {
                return true;
            }
        }
        if (!hasValidAcl) {
            return true;
        }
        return false;
    }
}
