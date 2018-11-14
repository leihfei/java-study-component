package com.lnlr.security.pojo.master.dao;

import com.lnlr.security.pojo.master.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author:leihfei
 * @description 部门管理持久层
 * @date:Create in 22:30 2018/9/3
 * @email:leihfein@gmail.com
 */

public interface SysUserDAO extends JpaRepository<SysUser, Integer>, JpaSpecificationExecutor<SysUser> {


    /**
     * 通过用户名查询
     *
     * @param username
     * @return
     */
    SysUser findAllByUsername(String username);

    /**
     * 通过电话号码查询
     *
     * @param telphone
     * @return
     */
    SysUser findAllByTelphone(String telphone);

    /**
     * 通过邮件查询
     *
     * @param email
     * @return
     */
    SysUser findAllByEmail(String email);

    /**
     * 通过用户名，电话号码，邮箱查询用户
     *
     * @param username
     * @param telphone
     * @param email
     * @return
     */
    SysUser findAllByUsernameOrTelphoneOrEmail(String username, String telphone, String email);

    /**
     * 通过部门查询用户
     * @param deptId
     * @return
     */
    List<SysUser> findAllByDeptId(Integer deptId);

}
