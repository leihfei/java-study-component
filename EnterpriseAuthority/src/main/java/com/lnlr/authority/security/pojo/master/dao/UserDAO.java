package com.lnlr.authority.security.pojo.master.dao;

import com.lnlr.authority.security.pojo.master.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author:leihfei
 * @description 权限管理系统用户持久层
 * @date:Create in 19:42 2018/8/30
 * @email:leihfein@gmail.com
 */
public interface UserDAO extends JpaRepository<SysUser, Integer>, JpaSpecificationExecutor<SysUser> {

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
     * @param keyword
     * @return
     */
    SysUser findAllByUsernameOrTelphoneOrEmail(String keyword);
}
