package com.lnlr.security.service.impl;

import com.lnlr.common.jpa.model.NgData;
import com.lnlr.common.jpa.model.NgPager;
import com.lnlr.common.jpa.query.DynamicSpecifications;
import com.lnlr.common.jpa.query.PageUtils;
import com.lnlr.security.pojo.master.dao.SysLogDAO;
import com.lnlr.security.pojo.master.entity.SysLog;
import com.lnlr.security.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:leihfei
 * @description: 权限记录业务实现
 * @date:Create in 0:09 2018/11/10
 * @email:leihfein@gmail.com
 */
@Service
@Slf4j
public class LogServiceImpl implements LogService {
    @Autowired
    private SysLogDAO logDAO;

    /**
     * @param sysLog
     * @return com.lnlr.security.pojo.master.entity.SysLog
     * @author: leihfei
     * @description 保存操作日志
     * @date: 0:11 2018/11/10
     * @email: leihfein@gmail.com
     */
    @Override
    public SysLog save(SysLog sysLog) {
        return logDAO.save(sysLog);
    }

    /**
     * @param ngPager
     * @return com.lnlr.common.jpa.model.NgData<com.lnlr.security.pojo.master.entity.SysLog>
     * @author: leihfei
     * @description 分页查询数据
     * @date: 0:11 2018/11/10
     * @email: leihfein@gmail.com
     */
    @Override
    public NgData<SysLog> page(NgPager ngPager) {
        return new NgData<>(logDAO.findAll(DynamicSpecifications.bySearchFilter(SysLog.class,
                PageUtils.buildSearchFilter(ngPager)), PageUtils.buildPageRequest(ngPager, PageUtils.buildSort(ngPager))), ngPager);
    }
}
