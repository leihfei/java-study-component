package com.lnlr.authority.security.service.impl;

import com.google.common.base.Preconditions;
import com.lnlr.authority.security.pojo.master.dao.UserDAO;
import com.lnlr.authority.security.pojo.master.dto.UserDTO;
import com.lnlr.authority.security.pojo.master.entity.SysUser;
import com.lnlr.authority.security.pojo.master.vo.UserVO;
import com.lnlr.authority.security.service.SysUserService;
import com.lnlr.common.exception.FaileResponseException;
import com.lnlr.common.exception.ParamException;
import com.lnlr.common.response.ObjectResponse;
import com.lnlr.common.response.Response;
import com.lnlr.common.utils.BeanValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author:leihfei
 * @description 权限管理系统用户业务实现
 * @date:Create in 19:47 2018/8/30
 * @email:leihfein@gmail.com
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<SysUser> findAll() {
        return userDAO.findAll();
    }

    /**
     * 创建用户
     *
     * @param userDTO
     * @return
     */
    @Override
    public Response create(UserDTO userDTO) {
        BeanValidator.check(userDTO);
        // 检查用户名，电话号码，Email，都不存在，那么就返回数据
        check(userDTO);
        // 初始化密码，盐值等
        String password = "123456";
        String salt = "2121345";
        // 构建
        SysUser sysUser = SysUser.builder()
                .email(userDTO.getEmail())
                .deptId(userDTO.getDeptId())
                .realName(userDTO.getRealName())
                .telphone(userDTO.getTelphone())
                .password(password)
                .status(userDTO.getStatus())
                .salt(salt)
                .username(userDTO.getUsername()).remark(userDTO.getRemark()).build();
        sysUser.setOperator("管理员");
        sysUser.setOperatorIp("127.0.0.1");
        sysUser.setOperatorTime(LocalDateTime.now());
        // TODO 发送邮件通知用户
        SysUser save = userDAO.save(sysUser);
        UserVO vo = new UserVO();
        Preconditions.checkNotNull(save, "保存用户失败");
        BeanUtils.copyProperties(save, vo);
        return new ObjectResponse<>(vo);
    }


    /**
     * @param userDTO
     * @return void
     * @author: leihfei
     * @description 检查数据是否存在
     * @date: 11:01 2018/9/8
     * @email: leihfein@gmail.com
     */
    private static void check(UserDTO userDTO) {
        if (checkUsername(userDTO.getUsername())) {
            throw new FaileResponseException("用户名已存在");
        }
        if (checkTelphone(userDTO.getTelphone())) {
            throw new FaileResponseException("电话号码已存在");
        }
        if (checkEmail(userDTO.getEmail())) {
            throw new FaileResponseException("邮箱已存在");
        }
    }

    /**
     * 更新用户
     *
     * @param userDTO
     * @return
     */
    @Override
    public Response update(UserDTO userDTO) {
        BeanValidator.check(userDTO);
        // 检查用户名，电话号码，Email，都不存在，那么就返回数据
        check(userDTO);
        SysUser befor = userDAO.findById(userDTO.getId()).orElse(null);
        Preconditions.checkNotNull(befor, "待更新的用户不存在");
        SysUser after = SysUser.builder()
                .email(userDTO.getEmail())
                .deptId(userDTO.getDeptId())
                .realName(userDTO.getRealName())
                .telphone(userDTO.getTelphone())
                .password(befor.getPassword())
                .status(userDTO.getStatus())
                .salt(befor.getSalt())
                .remark(userDTO.getRemark())
                .username(userDTO.getUsername()).build();
        SysUser save = userDAO.save(after);
        Preconditions.checkNotNull(save, "更新失败");
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(save, vo);
        return new ObjectResponse<>(vo);
    }

    /**
     * @param keyword 通过关键字查询用户
     * @return com.lnlr.authority.security.pojo.master.entity.SysUser
     * @author: leihfei
     * @description
     * @date: 11:38 2018/9/8
     * @email: leihfein@gmail.com
     */
    @Override
    public SysUser findKeyword(String keyword) {
        try {
            SysUser user = userDAO.findAllByUsernameOrTelphoneOrEmail(keyword);
            return user;
        } catch (Exception e) {
            throw new ParamException("用户数据异常，请联系管理员");
        } finally {
            return null;
        }
    }

    /**
     * @param username 用户名
     * @return 检查用户是否存在
     * @author: leihfei
     * @description
     * @date: 11:02 2018/9/8
     * @email: leihfein@gmail.com
     */
    private static boolean checkUsername(String username) {
        return false;
    }

    /**
     * @param telphone 电话号码
     * @return
     * @author: leihfei
     * @description 检查电话号码
     * @date: 11:02 2018/9/8
     * @email: leihfein@gmail.com
     */
    private static boolean checkTelphone(String telphone) {
        return false;
    }

    /**
     * @param email 邮箱
     * @return
     * @author: leihfei
     * @description 检查邮箱
     * @date: 11:03 2018/9/8
     * @email: leihfein@gmail.com
     */
    private static boolean checkEmail(String email) {
        return false;
    }
}
