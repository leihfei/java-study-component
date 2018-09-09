package com.lnlr.authority.security.service;

import com.lnlr.authority.security.pojo.master.dto.UserDTO;
import com.lnlr.authority.security.pojo.master.entity.SysUser;
import com.lnlr.common.response.Response;

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
     * @param userDTO
     * @return
     */
    Response create(UserDTO userDTO);

    /**
     * 更新用户
     *
     * @param userDTO
     * @return
     */
    Response update(UserDTO userDTO);

    /**
     * 通过关键字查询用户是否存在
     * @param keyword
     * @return
     */
    SysUser findKeyword(String keyword);
}
