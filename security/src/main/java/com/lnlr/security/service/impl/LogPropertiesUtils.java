package com.lnlr.security.service.impl;

import com.lnlr.common.constains.LogConstants;
import com.lnlr.common.utils.IpUtils;
import com.lnlr.common.utils.JsonUtils;
import com.lnlr.common.utils.RequestHolder;
import com.lnlr.security.pojo.master.entity.SysLog;

import java.time.LocalDateTime;

/**
 * @author:leihfei
 * @description: log日志属性控制
 * @date:Create in 0:21 2018/11/10
 * @email:leihfein@gmail.com
 */
public class LogPropertiesUtils {
    public static SysLog set(Integer type,Object oldValue,Object newValue,Integer targetId){
        SysLog sysLog = SysLog.builder()
                .oldValue(oldValue == null ?"":JsonUtils.object2Json(oldValue))
                .newValue(newValue == null ?"":JsonUtils.object2Json(newValue))
                .status(1).targetId(targetId).type(type)
                .operatorIp(IpUtils.getIpAddr(RequestHolder.currentRequest()))
                .operator(RequestHolder.currentUser().getRealName())
                .operatorTime(LocalDateTime.now())
                .build();
        return  sysLog;
    }
}
