package com.lnlr.security.service;

import com.lnlr.security.pojo.master.entity.SysUserRole;

import java.util.List;

/**
 * @author:leihfei
 * @description: 用户角色业务
 * @date:Create in 0:53 2018/11/1
 * @email:leihfein@gmail.com
 */
public interface SysUserRoleService {

    /**
     * 通过userid查询角色list
     *
     * @param userId
     * @return
     */
    List<SysUserRole> findAllRoleListByUserId(Integer userId);
}
