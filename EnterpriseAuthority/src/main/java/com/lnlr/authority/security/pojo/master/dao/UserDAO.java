package com.lnlr.authority.security.pojo.master.dao;

import com.lnlr.authority.security.pojo.master.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author:leihfei
 * @description 权限管理系统用户持久层
 * @date:Create in 19:42 2018/8/30
 * @email:leihfein@gmail.com
 */
public interface UserDAO extends JpaRepository<User,Integer> , JpaSpecificationExecutor<User> {
}
