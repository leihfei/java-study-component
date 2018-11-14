package com.lnlr.security.service.impl;

import com.google.common.base.Preconditions;
import com.lnlr.common.constains.LogConstants;
import com.lnlr.common.constains.SystemConstants;
import com.lnlr.common.entity.HashPassword;
import com.lnlr.common.exception.FaileResponseException;
import com.lnlr.common.exception.ParamException;
import com.lnlr.common.jpa.model.NgData;
import com.lnlr.common.jpa.model.NgPager;
import com.lnlr.common.jpa.query.DynamicSpecifications;
import com.lnlr.common.jpa.query.PageUtils;
import com.lnlr.common.response.FailedResponse;
import com.lnlr.common.response.ObjectResponse;
import com.lnlr.common.response.Response;
import com.lnlr.common.utils.*;
import com.lnlr.security.pojo.master.dao.SysDeptDAO;
import com.lnlr.security.pojo.master.dao.SysLogDAO;
import com.lnlr.security.pojo.master.dao.SysUserDAO;
import com.lnlr.security.pojo.master.dto.CheckPassParam;
import com.lnlr.security.pojo.master.dto.UserParam;
import com.lnlr.security.pojo.master.entity.SysDept;
import com.lnlr.security.pojo.master.entity.SysUser;
import com.lnlr.security.pojo.master.vo.dept.DeptShortVO;
import com.lnlr.security.pojo.master.vo.user.UserDeptVO;
import com.lnlr.security.pojo.master.vo.user.UserVO;
import com.lnlr.security.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author:leihfei
 * @description 权限管理系统用户业务实现
 * @date:Create in 19:47 2018/8/30
 * @email:leihfein@gmail.com
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDAO userDAO;

    @Autowired
    private SysDeptDAO deptDAO;

    @Autowired
    private SysLogDAO logDAO;


    @Override
    public List<SysUser> findAll() {
        return userDAO.findAll();
    }

    /**
     * 创建用户
     *
     * @param userParam
     * @return
     */
    @Override
    public Response create(UserParam userParam) {
        BeanValidator.check(userParam);
        // 检查用户名，电话号码，Email，都不存在，那么就返回数据
        check(userParam);
        // 初始化密码，盐值等
        HashPassword hashPassword = PassUtils.encryptPassword(SystemConstants.DEFAULT_PASSWORD);
        // 构建
        SysUser sysUser = SysUser.builder()
                .email(userParam.getEmail())
                .deptId(userParam.getDeptId())
                .realName(userParam.getRealName())
                .telphone(userParam.getTelphone())
                .password(hashPassword.getPassword())
                .status(userParam.getStatus())
                .salt(hashPassword.getSalt())
                .username(userParam.getUsername()).remark(userParam.getRemark()).build();
        sysUser.setOperator(RequestHolder.currentUser().getRealName());
        sysUser.setOperatorIp(IpUtils.getUserIP(RequestHolder.currentRequest()));
        sysUser.setOperatorTime(LocalDateTime.now());
        // TODO 发送邮件通知用户
        SysUser save = userDAO.save(sysUser);
        Preconditions.checkNotNull(save, "新增用户失败");
        UserVO vo = CopyUtils.beanCopy(save, new UserVO());
        // 保存日志
        logDAO.save( LogPropertiesUtils.set(LogConstants.TYPE_USER, null, save, save.getId()));
        return new ObjectResponse<>(vo);
    }


    /**
     * @param userParam
     * @return void
     * @author: leihfei
     * @description 检查数据是否存在
     * @date: 11:01 2018/9/8
     * @email: leihfein@gmail.com
     */
    private void check(UserParam userParam) {
        if (checkUsername(userParam.getUsername())) {
            throw new FaileResponseException("用户名已存在");
        }
        if (checkTelphone(userParam.getTelphone())) {
            throw new FaileResponseException("电话号码已存在");
        }
        if (checkEmail(userParam.getEmail())) {
            throw new FaileResponseException("邮箱已存在");
        }
    }

    /**
     * 更新用户
     *
     * @param userParam
     * @return
     */
    @Override
    public Response update(UserParam userParam) {
        if (userParam.getId() == null) {
            return new FailedResponse<>("更新时Id不能为空!");
        }
        SysUser befor = userDAO.findById(userParam.getId()).orElse(null);
        SysUser oldUser = new SysUser();
        BeanUtils.copyProperties(befor, oldUser);
        Preconditions.checkNotNull(befor, "待更新的用户不存在");
        try {
            CopyUtils.copy(befor, userParam);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        BeanValidator.check(befor);
        // 检查用户名，电话号码，Email，存在任意一个就抛出异常
        checkUpdate(befor, oldUser);
        befor.setOperator(RequestHolder.currentUser().getRealName());
        befor.setOperatorIp(IpUtils.getUserIP(RequestHolder.currentRequest()));
        SysUser save = userDAO.save(befor);
        Preconditions.checkNotNull(save, "更新用户失败");
        UserVO vo = CopyUtils.beanCopy(save, new UserVO());
        // 保存日志
        logDAO.save( LogPropertiesUtils.set(LogConstants.TYPE_DEPT, befor, save, save.getId()));
        return new ObjectResponse<>(vo);
    }

    /**
     * 检查更新
     *
     * @param befor
     * @param oldUser
     */
    private void checkUpdate(SysUser befor, SysUser oldUser) {
        if (!oldUser.getUsername().equals(befor.getUsername()) && checkUsername(befor.getUsername())) {
            throw new FaileResponseException("待更新的用户名已存在!");
        }
        if (!oldUser.getTelphone().equals(befor.getTelphone()) && checkTelphone(befor.getTelphone())) {
            throw new FaileResponseException("待更新的电话号码已存在!");
        }
        if (!oldUser.getEmail().equals(befor.getEmail()) && checkEmail(befor.getEmail())) {
            throw new FaileResponseException("待更新的邮件已存在!");
        }
    }

    /**
     * @param keyword 通过关键字查询用户
     * @return SysUser
     * @author: leihfei
     * @description
     * @date: 11:38 2018/9/8
     * @email: leihfein@gmail.com
     */
    @Override
    public SysUser findKeyword(String keyword) {
        SysUser user = null;
        if (!StringUtils.isNotEmpty(keyword)) {
            throw new ParamException("用户信息异常，无法查询");
        }
        try {
            user = userDAO.findAllByUsernameOrTelphoneOrEmail(keyword, keyword, keyword);
        } catch (Exception e) {
            throw new ParamException("用户数据异常，请联系管理员");
        } finally {
            return user;
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
    private boolean checkUsername(String username) {
        SysUser allByUsername = userDAO.findAllByUsername(username);
        return allByUsername != null ? true : false;
    }

    /**
     * @param telphone 电话号码
     * @return
     * @author: leihfei
     * @description 检查电话号码
     * @date: 11:02 2018/9/8
     * @email: leihfein@gmail.com
     */
    private boolean checkTelphone(String telphone) {
        return userDAO.findAllByTelphone(telphone) != null ? true : false;
    }

    /**
     * @param email 邮箱
     * @return
     * @author: leihfei
     * @description 检查邮箱
     * @date: 11:03 2018/9/8
     * @email: leihfein@gmail.com
     */
    private boolean checkEmail(String email) {
        return userDAO.findAllByEmail(email) != null ? true : false;
    }


    @Override
    public SysUser view(Integer id) {
        return userDAO.findById(id).orElse(null);
    }

    /**
     * 分页查询用户数据
     *
     * @param ngPager
     * @return
     */
    @Override
    public NgData page(NgPager ngPager) {
        NgData<SysUser> ngData = new NgData<>(
                userDAO.findAll(DynamicSpecifications.bySearchFilter(SysUser.class,
                        PageUtils.buildSearchFilter(ngPager)), PageUtils.buildPageRequest(ngPager, PageUtils.buildSort(ngPager))), ngPager);
        // 处理数据
        if (ngData == null) {
            return ngData;
        }
        List<SysDept> all = deptDAO.findAll();
        if (all != null && all.isEmpty()) {
            throw new RuntimeException("部门数据异常!");
        }
        // 将部门转为map
        Map<Integer, SysDept> deptMap = all.stream().collect(Collectors.toMap(SysDept::getId, a -> a, (k1, k2) -> k1));
        // 复制一份用户数据
        NgData<UserDeptVO> vos = new NgData();
        BeanUtils.copyProperties(ngData, vos);
        vos.setData(new ArrayList<>());
        ngData.getData().forEach(user -> {
            // 得到每一个用户
            SysDept sysDept = deptMap.get(Integer.parseInt(user.getDeptId()));
            UserDeptVO vo = new UserDeptVO();
            BeanUtils.copyProperties(user, vo);
            vo.setDept(new DeptShortVO(sysDept.getId(), sysDept.getName()));
            vos.getData().add(vo);
        });

        return vos;
    }

    @Override
    public boolean updatePass(CheckPassParam checkPassParam) {
        Preconditions.checkNotNull(checkPassParam.getNewPass(), "新密码不能为空!");
        boolean b = checkPass(checkPassParam);
        if (b == false) {
            throw new RuntimeException("用户不存在或密码错误!");
        }
        // 更新密码
        SysUser sysUser = userDAO.findById(checkPassParam.getUserId()).orElse(null);
        HashPassword hashPassword = PassUtils.encryptPassword(checkPassParam.getNewPass());
        sysUser.setPassword(hashPassword.getPassword());
        sysUser.setSalt(hashPassword.getSalt());
        userDAO.save(sysUser);
        return true;
    }

    @Override
    public boolean checkPass(CheckPassParam checkPassParam) {
        BeanValidator.check(checkPassParam);
        // 检查用户是否存在
        SysUser sysUser = userDAO.findById(checkPassParam.getUserId()).orElse(null);
        Preconditions.checkNotNull(sysUser, "用户不存在!");
        boolean b = PassUtils.validatePassword(checkPassParam.getOldPass(), sysUser.getPassword(), sysUser.getSalt());
        return b;

    }

    @Override
    public SysUser findById(Integer id) {
        return userDAO.findById(id).orElse(null);
    }

    @Override
    public List<SysUser> findAllByDeptId(Integer id) {
        return userDAO.findAllByDeptId(id);
    }
}
