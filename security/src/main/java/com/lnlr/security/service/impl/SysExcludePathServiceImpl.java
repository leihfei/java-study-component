package com.lnlr.security.service.impl;

import com.lnlr.security.pojo.master.dao.SysDefaultFilterDAO;
import com.lnlr.security.pojo.master.entity.SysDefaultFilter;
import com.lnlr.security.service.SysExcludePathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:leihfei
 * @description: 系统放过拦截器实现
 * @date:Create in 11:21 2018/10/26
 * @email:leihfein@gmail.com
 */
@Service
public class SysExcludePathServiceImpl implements SysExcludePathService {

    @Autowired
    private SysDefaultFilterDAO sysDefaultFilterDAO;

    @Override
    public List<SysDefaultFilter> findAll() {
        return sysDefaultFilterDAO.findAll();
    }
}
