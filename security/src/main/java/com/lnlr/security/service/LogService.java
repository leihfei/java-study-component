package com.lnlr.security.service;

import com.lnlr.common.jpa.model.NgData;
import com.lnlr.common.jpa.model.NgPager;
import com.lnlr.security.pojo.master.entity.SysLog;

/**
 * @author:leihfei
 * @description: 日志业务接口
 * @date:Create in 0:08 2018/11/10
 * @email:leihfein@gmail.com
 */
public interface LogService {
    SysLog save(SysLog sysLog);

    NgData<SysLog> page(NgPager ngPager);
}
