package com.lnlr.security.service;

import com.lnlr.common.entity.IdEntity;
import com.lnlr.common.jpa.model.NgData;
import com.lnlr.common.jpa.model.NgPager;
import com.lnlr.common.response.Response;
import com.lnlr.security.pojo.master.dto.AclParam;
import com.lnlr.security.pojo.master.entity.SysAcl;

import java.util.List;

/**
 * @author:leihfei
 * @description 权限点业务
 * @date:Create in 10:42 2018/10/30
 * @email:leihfein@gmail.com
 */
public interface SysAclService {

    Response create(AclParam aclParam);

    Response update(AclParam aclParam);

    void delete(IdEntity idEntity);

    NgData page(NgPager ngPager);


    List<SysAcl> findAll();

    List<SysAcl> findAllByIds(List<Integer> ids);

    List<SysAcl> findAllByModuleId(Integer id);

    List<SysAcl> findAllByUrl(String url);
}
