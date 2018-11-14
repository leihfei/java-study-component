package com.lnlr.dynamic.pojo.master.dao;

import com.lnlr.dynamic.pojo.master.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 11:39 2018/8/29
 * @email:leihfein@gmail.com
 */
public interface UserDao1 extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
}
