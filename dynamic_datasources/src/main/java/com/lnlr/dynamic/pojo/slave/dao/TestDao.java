package com.lnlr.dynamic.pojo.slave.dao;

import com.lnlr.dynamic.pojo.slave.entity.TestSecond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 11:41 2018/8/29
 * @email:leihfein@gmail.com
 */
public interface TestDao extends JpaRepository<TestSecond, Integer>, JpaSpecificationExecutor<TestSecond> {

}
