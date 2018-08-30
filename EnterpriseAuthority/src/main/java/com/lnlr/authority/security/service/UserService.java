package com.lnlr.authority.security.service;

import com.lnlr.authority.security.pojo.master.entity.User;

import java.util.List;

/**
 * @author:leihfei
 * @description 权限管理系统用户业务接口
 * @date:Create in 19:46 2018/8/30
 * @email:leihfein@gmail.com
 */
public interface UserService {
    /**
     * @param
     * @return
     * @author: leihfei
     * @description 查询所有用户数据
     * @date: 19:47 2018/8/30
     * @email: leihfein@gmail.com
     */
    List<User> findAll();
}
