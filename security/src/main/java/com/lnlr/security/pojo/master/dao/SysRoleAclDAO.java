package com.lnlr.security.pojo.master.dao;

import com.lnlr.security.pojo.master.entity.SysRoleAcl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
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

    @Modifying
    @Transactional
    @Query(value = "delete from SysRoleAcl es where es.roleId = ?1")
    void deleteAllByRoleId(int roleId);
}
