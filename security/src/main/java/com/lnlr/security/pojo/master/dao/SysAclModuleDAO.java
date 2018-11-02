package com.lnlr.security.pojo.master.dao;

import com.lnlr.security.pojo.master.entity.SysAclModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author:leihfei
 * @description: 权限模块持久层
 * @date:Create in 16:49 2018/10/26
 * @email:leihfein@gmail.com
 */
public interface SysAclModuleDAO extends JpaRepository<SysAclModule, Integer>, JpaSpecificationExecutor<SysAclModule> {
    List<SysAclModule> findAllByLevelLike(String s);

    /**
     * 通过权限模块名称和父节点id查询权限模块
     *
     * @param name     权限名称
     * @param parentId 父节点id
     * @return
     */
    List<SysAclModule> findAllByNameAndParentId(String name, Integer parentId);


    /**
     * 通过父节点id查询数据
     *
     * @param id
     * @return
     */
    List<SysAclModule> findAllByParentId(Integer id);
}
