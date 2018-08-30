package com.lnlr.dynamic.service.impl;

import com.lnlr.dynamic.pojo.master.dao.UserDao;
import com.lnlr.dynamic.pojo.slave.dao.TestDao;
import com.lnlr.dynamic.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:leihfei
 * @description
 * @date:Create in 11:44 2018/8/29
 * @email:leihfein@gmail.com
 */
@Service
public class TestServiceImpl implements TestService {

    /**
     * 第一个数据源数据
     */
    @Autowired
    private UserDao userDao;

    /**
     * 第二数据源数据
     */
    @Autowired
    private TestDao testDao;


    /**
     * @param
     * @return 双数据源数据查询结果
     * @author: leihfei
     * @description 使用双数据源查询数据
     * @date: 17:28 2018/8/30
     * @email: leihfein@gmail.com
     */
    @Override
    public List<Object> find() {
        List list = new ArrayList();
        list.add(userDao.findAll());
        list.add(testDao.findAll());
        return list;
    }
}
