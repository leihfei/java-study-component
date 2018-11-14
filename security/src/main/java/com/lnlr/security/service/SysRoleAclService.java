package com.lnlr.security.service;

import com.lnlr.common.response.Response;
import com.lnlr.security.pojo.master.entity.SysRoleAcl;

import java.util.List;

/**
 * @author:leihfei
 * @description: 角色和权限业务
 * @date:Create in 0:58 2018/11/1
 * @email:leihfein@gmail.com
 */
public interface SysRoleAclService {

    /**
     * 通过角色Id查询权限列表
     *
     * @param ids 角色id
     * @return
     */
    List<SysRoleAcl> findAllByRoleIdList(List<Integer> ids);


    /**
     *  更改角色权限
     * @param roleId
     * @param ids
     * @return
     */
    Response changeRoleAcls(Integer roleId, List<Integer> ids);
}
