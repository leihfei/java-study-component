package com.lnlr.security.pojo.master.dao;

import com.lnlr.security.pojo.master.entity.SysDefaultFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author:leihfei
 * @description 默认数据库加载过滤器地址
 * @date:Create in 9:24 2018/10/9
 * @email:leihfein@gmail.com
 */
public interface SysDefaultFilterDAO extends JpaRepository<SysDefaultFilter, Integer>, JpaSpecificationExecutor<SysDefaultFilter> {
}
