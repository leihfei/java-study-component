package com.lnlr.security.pojo.master.dao;

import com.lnlr.security.pojo.master.entity.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author:leihfei
 * @description: 角色用户持久层
 * @date:Create in 16:52 2018/10/26
 * @email:leihfein@gmail.com
 */
public interface SysUserRoleDAO extends JpaRepository<SysUserRole, Integer>, JpaSpecificationExecutor<SysUserRole> {

    List<SysUserRole> findAllByUserId(Integer userId);
}