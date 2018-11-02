package com.lnlr.security.service.impl;

import com.google.common.base.Preconditions;
import com.lnlr.common.entity.IdEntity;
import com.lnlr.common.jpa.model.NgData;
import com.lnlr.common.jpa.model.NgPager;
import com.lnlr.common.jpa.query.DynamicSpecifications;
import com.lnlr.common.jpa.query.PageUtils;
import com.lnlr.common.response.ObjectResponse;
import com.lnlr.common.response.Response;
import com.lnlr.common.utils.BeanValidator;
import com.lnlr.common.utils.CopyUtils;
import com.lnlr.common.utils.IpUtils;
import com.lnlr.common.utils.RequestHolder;
import com.lnlr.security.pojo.master.dao.SysRoleDAO;
import com.lnlr.security.pojo.master.dto.RoleParam;
import com.lnlr.security.pojo.master.entity.SysRole;
import com.lnlr.security.pojo.master.vo.role.RoleVO;
import com.lnlr.security.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author:leihfei
 * @description: 角色业务实现
 * @date:Create in 16:25 2018/10/30
 * @email:leihfein@gmail.com
 */
@Service
@Slf4j
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDAO roleDAO;

    @Override
    public Response create(RoleParam roleParam) {
        BeanValidator.check(roleParam);
        if (checkExist(roleParam.getName(), null)) {
            throw new RuntimeException("角色名称已存在!");
        }
        SysRole role = SysRole.builder().name(roleParam.getName()).status(roleParam.getStatus()).type(roleParam.getType()).remark(roleParam.getRemark()).build();
        role.setOperator(RequestHolder.currentUser().getRealName());
        role.setOperatorIp(IpUtils.getUserIP(RequestHolder.currentRequest()));
        role.setOperatorTime(LocalDateTime.now());
        SysRole save = roleDAO.save(role);
        Preconditions.checkNotNull(save, "新增角色失败!");
        // 封装返回数据
        RoleVO vo = CopyUtils.beanCopy(save, new RoleVO());
        return new ObjectResponse<>(vo);
    }

    @Override
    public Response update(RoleParam roleParam) {
        if (roleParam.getId() == null) {
            throw new RuntimeException("角色数据异常，Id为空，无法更新");
        }
        SysRole befor = roleDAO.findById(roleParam.getId()).orElse(null);
        Preconditions.checkNotNull(befor, "角色数据不存在!");
        SysRole after = CopyUtils.beanCopy(befor, new SysRole());
        // 进行数据拷贝
        try {
            CopyUtils.copy(after, roleParam);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        // 检查数据
        BeanValidator.check(after);
        // 再次审查数据
        if (checkExist(after.getName(), after.getId())) {
            throw new RuntimeException("角色名称已存在!");
        }
        // 进行保存
        SysRole save = roleDAO.save(after);
        Preconditions.checkNotNull(save, "角色数据更新失败！");
        RoleVO vo = CopyUtils.beanCopy(save, new RoleVO());
        return new ObjectResponse<>(vo);
    }

    @Override
    public NgData page(NgPager ngPager) {
        return new NgData<>(roleDAO.findAll(DynamicSpecifications.bySearchFilter(SysRole.class,
                PageUtils.buildSearchFilter(ngPager)), PageUtils.buildPageRequest(ngPager, PageUtils.buildSort(ngPager))), ngPager);
    }

    /**
     * @param idEntity 包含id数据
     * @return void
     * @author: leihfei
     * @description 删除角色信息
     * @date: 18:22 2018/10/30
     * @email: leihfein@gmail.com
     */
    @Override
    public void delete(IdEntity idEntity) {

    }

    @Override
    public List<SysRole> list() {
        return roleDAO.findAll();
    }



    private boolean checkExist(String name, Integer id) {
        List<SysRole> list = roleDAO.findAllByName(name);
        if (list != null && !list.isEmpty()) {
            if (list.size() == 1 && id != null && list.get(0).getId() == id) {
                return false;
            }
            return true;
        }
        return false;
    }
}
