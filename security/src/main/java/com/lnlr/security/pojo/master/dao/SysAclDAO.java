package com.lnlr.security.pojo.master.dao;

import com.lnlr.security.pojo.master.entity.SysAcl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
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

    /**
     * 通过模块Id查询权限点
     *
     * @param id
     * @return
     */
    List<SysAcl> findAllByAclModuleId(Integer id);

    /**
     * 查询匹配所有满足url的数据
     *  主要用于动态参数/sys/acl/view/1
     * @param url
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM sys_acl where url is not null and url <> '' and   ?1 REGEXP url", nativeQuery = true)
    List<SysAcl> findAllByUrl(String url);
}
