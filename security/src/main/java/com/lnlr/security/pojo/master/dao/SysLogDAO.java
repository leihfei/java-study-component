package com.lnlr.security.pojo.master.dao;

import com.lnlr.security.pojo.master.entity.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author:leihfei
 * @description: 权限操作日志持久层
 * @date:Create in 16:50 2018/10/26
 * @email:leihfein@gmail.com
 */
public interface SysLogDAO extends JpaRepository<SysLog, Integer>, JpaSpecificationExecutor<SysLog> {
}
