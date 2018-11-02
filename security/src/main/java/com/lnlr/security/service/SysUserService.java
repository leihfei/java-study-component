package com.lnlr.security.service;

import com.lnlr.common.jpa.model.NgData;
import com.lnlr.common.jpa.model.NgPager;
import com.lnlr.common.response.Response;
import com.lnlr.security.pojo.master.dto.CheckPassParam;
import com.lnlr.security.pojo.master.dto.UserParam;
import com.lnlr.security.pojo.master.entity.SysUser;

import java.util.List;

/**
 * @author:leihfei
 * @description 权限管理系统用户业务接口
 * @date:Create in 19:46 2018/8/30
 * @email:leihfein@gmail.com
 */
public interface SysUserService {
    /**
     * @param
     * @return
     * @author: leihfei
     * @description 查询所有用户数据
     * @date: 19:47 2018/8/30
     * @email: leihfein@gmail.com
     */
    List<SysUser> findAll();

    /**
     * 创建用户
     *
     * @param userParam
     * @return
     */
    Response create(UserParam userParam);

    /**
     * 更新用户
     *
     * @param userParam
     * @return
     */
    Response update(UserParam userParam);

    /**
     * 通过关键字查询用户是否存在
     *
     * @param keyword
     * @return
     */
    SysUser findKeyword(String keyword);

    /**
     * 通过id查询数据
     *
     * @param id
     * @return
     */
    SysUser view(Integer id);

    /**
     * 分页查询用户数据
     *
     * @param ngPager
     * @return
     */
    NgData page(NgPager ngPager);

    /**
     * 更新密码
     * @param checkPassParam
     * @return
     */
    boolean updatePass(CheckPassParam checkPassParam);

    /**
     * 检查旧密码
     * @param checkPassParam
     * @return
     */
    boolean checkPass(CheckPassParam checkPassParam);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    SysUser findById(Integer id);
}
