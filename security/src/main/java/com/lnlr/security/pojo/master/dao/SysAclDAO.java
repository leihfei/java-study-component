package com.lnlr.security.pojo.master.dao;

import com.lnlr.security.pojo.master.entity.SysAcl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author:leihfei
 * @description: 权限点持久层
 * @date:Create in 16:48 2018/10/26
 * @email:leihfein@gmail.com
 */
public interface SysAclDAO extends JpaRepository<SysAcl, Integer>, JpaSpecificationExecutor<SysAcl> {

    /**
     * 通过权限模块id，权限点名称查询数据
     *
     * @param aclModuleId
     * @param name
     * @return
     */
    List<SysAcl> findAllByAclModuleIdAndName(Integer aclModuleId, String name);
}
