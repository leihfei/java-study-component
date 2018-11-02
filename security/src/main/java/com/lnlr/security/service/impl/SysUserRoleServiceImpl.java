package com.lnlr.security.service.impl;

import com.lnlr.security.pojo.master.dao.SysUserRoleDAO;
import com.lnlr.security.pojo.master.entity.SysUserRole;
import com.lnlr.security.service.SysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:leihfei
 * @description: 用户角色业务实现
 * @date:Create in 0:54 2018/11/1
 * @email:leihfein@gmail.com
 */
@Service
@Slf4j
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Autowired
    private SysUserRoleDAO userRoleDAO;

    @Override
    public List<SysUserRole> findAllRoleListByUserId(Integer userId) {
        return userRoleDAO.findAllByUserId(userId);
    }
}
