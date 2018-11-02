package com.lnlr.security.pojo.master.dao;

import com.lnlr.security.pojo.master.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author:leihfei
 * @description: 角色持久层
 * @date:Create in 16:50 2018/10/26
 * @email:leihfein@gmail.com
 */
public interface SysRoleDAO extends JpaRepository<SysRole, Integer>, JpaSpecificationExecutor<SysRole> {

    List<SysRole> findAllByName(String name);

}
