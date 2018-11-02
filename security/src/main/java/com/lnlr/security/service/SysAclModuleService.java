package com.lnlr.security.service;

import com.lnlr.common.entity.IdEntity;
import com.lnlr.common.jpa.model.NgData;
import com.lnlr.common.jpa.model.NgPager;
import com.lnlr.common.response.Response;
import com.lnlr.security.pojo.master.dto.AclModuleParam;

/**
 * @author:leihfei
 * @description: 权限模块业务层
 * @date:Create in 17:03 2018/10/26
 * @email:leihfein@gmail.com
 */
public interface SysAclModuleService {

    Response create(AclModuleParam aclModuleParam);

    Response update(AclModuleParam aclModuleParam);

    Response delete(IdEntity idEntity);

    NgData page(NgPager ngPager);
}
