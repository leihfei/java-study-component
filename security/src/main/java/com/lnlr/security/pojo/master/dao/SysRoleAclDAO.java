package com.lnlr.security.pojo.master.dao;

import com.lnlr.security.pojo.master.entity.SysRoleAcl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author:leihfei
 * @description: 角色权限持久层
 * @date:Create in 16:51 2018/10/26
 * @email:leihfein@gmail.com
 */
public interface SysRoleAclDAO extends JpaRepository<SysRoleAcl, Integer>, JpaSpecificationExecutor<SysRoleAcl> {

    /**
     * 通过角色Id查询list
     *
     * @param ids
     * @return
     */
    List<SysRoleAcl> findAllByRoleIdIn(List<Integer> ids);
}
