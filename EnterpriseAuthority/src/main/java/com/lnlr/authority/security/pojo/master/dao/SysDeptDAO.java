package com.lnlr.authority.security.pojo.master.dao;

import com.lnlr.authority.security.pojo.master.entity.SysDept;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author:leihfei
 * @description 部门管理持久层
 * @date:Create in 22:30 2018/9/3
 * @email:leihfein@gmail.com
 */
public interface SysDeptDAO extends JpaRepository<SysDept, Integer>, JpaSpecificationExecutor<SysDept> {

    /**
     * 通过level查询所有数据
     *
     * @param level
     * @return
     */
    List<SysDept> findAllByLevelLike(String level);

    /**
     * 通过部门名称和父节点id查询部门
     *
     * @param name     部门名称
     * @param parentId 父节点id
     * @return
     */
    List<SysDept> findAllByNameAndParentId(String name, Integer parentId);


    /**
     * 通过部门parentId查询数据
     * @param parentId
     * @return
     */
    List<SysDept> findAllByParentId(Integer parentId);
}
