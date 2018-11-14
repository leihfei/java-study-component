package com.lnlr.security.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.lnlr.common.constains.LogConstants;
import com.lnlr.common.response.Response;
import com.lnlr.common.response.SuccessResponse;
import com.lnlr.common.utils.IpUtils;
import com.lnlr.common.utils.RequestHolder;
import com.lnlr.security.pojo.master.dao.SysLogDAO;
import com.lnlr.security.pojo.master.dao.SysRoleAclDAO;
import com.lnlr.security.pojo.master.entity.SysRoleAcl;
import com.lnlr.security.service.SysRoleAclService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author:leihfei
 * @description: 角色-权限业务实现
 * @date:Create in 0:59 2018/11/1
 * @email:leihfein@gmail.com
 */
@Service
@Slf4j
public class SysRoleAclServiceImpl implements SysRoleAclService {

    @Autowired
    private SysRoleAclDAO roleAclDAO;

    @Autowired
    private SysLogDAO logDAO;


    /**
     * @param ids 角色Id
     * @return java.util.List<com.lnlr.security.pojo.master.entity.SysRoleAcl>
     * @author: leihfei
     * @description 通过角色ID查询权限列表
     * @date: 1:01 2018/11/1
     * @email: leihfein@gmail.com
     */
    @Override
    public List<SysRoleAcl> findAllByRoleIdList(List<Integer> ids) {
        return roleAclDAO.findAllByRoleIdIn(ids);
    }

    @Override
    public Response changeRoleAcls(Integer roleId, List<Integer> ids) {
        // 1. 取出当前角色的所有权限点
        List<Integer> originAclIdList = Lists.newArrayList();
        // 比对当前长度和数据库权限点是否相同
        if (originAclIdList.size() == ids.size()) {
            Set<Integer> originAclSet = Sets.newHashSet(originAclIdList);
            Set<Integer> aclIdSet = Sets.newHashSet(ids);
            originAclSet.removeAll(aclIdSet);
            if (CollectionUtils.isEmpty(originAclSet)) {
                return new SuccessResponse("权限角色更新成功!");
            }
        }
        // 进行角色权限更新
        updateRoleAcl(roleId, ids);
        // 保存日志
        logDAO.save(LogPropertiesUtils.set(LogConstants.TYPE_ROLE_ACL, originAclIdList, ids, roleId));
        return new SuccessResponse("更新成功!");
    }

    @Transactional
    public void updateRoleAcl(int roleId, List<Integer> aclIdList) {
        // 1. 先删除角色权限点
        roleAclDAO.deleteAllByRoleId(roleId);
        // 2. 生成权限角色，批量插入
        List<SysRoleAcl> acls = Lists.newArrayList();
        aclIdList.forEach(item -> {
            SysRoleAcl build = SysRoleAcl.builder().roleId(roleId).aclId(item).operator(RequestHolder.currentUser().getRealName())
                    .operatorIp(IpUtils.getRemoteIp(RequestHolder.currentRequest())).operatorTime(LocalDateTime.now()).build();
            acls.add(build);
        });
        roleAclDAO.saveAll(acls);
    }
}
