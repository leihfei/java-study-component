package com.lnlr.security.service.impl;

import com.lnlr.security.pojo.master.dao.SysRoleAclDAO;
import com.lnlr.security.pojo.master.entity.SysRoleAcl;
import com.lnlr.security.service.SysRoleAclService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:leihfei
 * @description: 角色-权限业务实现
 * @date:Create in 0:59 2018/11/1
 * @email:leihfein@gmail.com
 */
@Service
@Slf4j
public class SysRoleAclServiceImpl implements SysRoleAclService {

    @Autowired
    private SysRoleAclDAO roleAclDAO;


    /**
     * @param ids 角色Id
     * @return java.util.List<com.lnlr.security.pojo.master.entity.SysRoleAcl>
     * @author: leihfei
     * @description 通过角色ID查询权限列表
     * @date: 1:01 2018/11/1
     * @email: leihfein@gmail.com
     */
    @Override
    public List<SysRoleAcl> findAllByRoleIdList(List<Integer> ids) {
        return roleAclDAO.findAllByRoleIdIn(ids);
    }
}
