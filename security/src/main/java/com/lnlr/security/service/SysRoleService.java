package com.lnlr.security.service;

import com.lnlr.common.entity.IdEntity;
import com.lnlr.common.jpa.model.NgData;
import com.lnlr.common.jpa.model.NgPager;
import com.lnlr.common.response.Response;
import com.lnlr.security.pojo.master.dto.RoleParam;
import com.lnlr.security.pojo.master.entity.SysRole;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @author:leihfei
 * @description: 角色业务
 * @date:Create in 16:24 2018/10/30
 * @email:leihfein@gmail.com
 */
public interface SysRoleService {

    Response create(RoleParam roleParam);

    Response update(RoleParam roleParam);

    NgData page(NgPager ngPager);

    void delete(IdEntity idEntity);

    List<SysRole> list();

}
