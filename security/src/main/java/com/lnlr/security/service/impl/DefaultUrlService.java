package com.lnlr.security.service.impl;

import com.lnlr.security.pojo.master.dao.SysDefaultFilterDAO;
import com.lnlr.security.pojo.master.entity.SysDefaultFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 9:39 2018/10/9
 * @email:leihfein@gmail.com
 */
@Service
public class DefaultUrlService {
    @Autowired
    private SysDefaultFilterDAO sysDefaultFilterDAO;

    public List<SysDefaultFilter> findAll() {
        return sysDefaultFilterDAO.findAll(new Sort(Sort.Direction.ASC, "sort"));
    }
}
