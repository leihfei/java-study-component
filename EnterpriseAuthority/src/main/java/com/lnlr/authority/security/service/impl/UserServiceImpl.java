package com.lnlr.authority.security.service.impl;

import com.lnlr.authority.security.pojo.master.dao.UserDAO;
import com.lnlr.authority.security.pojo.master.entity.User;
import com.lnlr.authority.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:leihfei
 * @description 权限管理系统用户业务实现
 * @date:Create in 19:47 2018/8/30
 * @email:leihfein@gmail.com
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }
}
