package com.lnlr.security.service.impl;

import com.google.common.base.Preconditions;
import com.lnlr.common.constains.LogConstants;
import com.lnlr.common.entity.IdEntity;
import com.lnlr.common.exception.FaileResponseException;
import com.lnlr.common.jpa.model.NgData;
import com.lnlr.common.jpa.model.NgPager;
import com.lnlr.common.jpa.query.DynamicSpecifications;
import com.lnlr.common.jpa.query.PageUtils;
import com.lnlr.common.response.FailedResponse;
import com.lnlr.common.response.ObjectResponse;
import com.lnlr.common.response.Response;
import com.lnlr.common.response.SuccessResponse;
import com.lnlr.common.utils.*;
import com.lnlr.security.pojo.master.dao.SysAclModuleDAO;
import com.lnlr.security.pojo.master.dao.SysLogDAO;
import com.lnlr.security.pojo.master.dto.AclModuleParam;
import com.lnlr.security.pojo.master.entity.SysAcl;
import com.lnlr.security.pojo.master.entity.SysAclModule;
import com.lnlr.security.pojo.master.vo.module.AclModuleVO;
import com.lnlr.security.service.SysAclModuleService;
import com.lnlr.security.service.SysAclService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author:leihfei
 * @description: 权限模块业务实现
 * @date:Create in 17:04 2018/10/26
 * @email:leihfein@gmail.com
 */
@Service
@Slf4j
public class SysAclModuleServiceImpl implements SysAclModuleService {

    @Autowired
    private SysAclModuleDAO aclModuleDAO;

    @Autowired
    private SysAclService aclService;

    @Autowired
    private SysLogDAO logDAO;

    @Override
    public Response create(AclModuleParam aclModuleParam) {
        BeanValidator.check(aclModuleParam);
        if (checkExist(aclModuleParam.getParentId(), aclModuleParam.getName(), null)) {
            throw new RuntimeException("同一模块下不能存在相同名称!");
        }
        SysAclModule sysAclModule = SysAclModule.builder().level(LevelUtils.calculatelevel(getLevel(aclModuleParam.getParentId()), aclModuleParam.getParentId())).name(aclModuleParam.getName())
                .parentId(aclModuleParam.getParentId()).seq(aclModuleParam.getSeq())
                .remark(aclModuleParam.getRemark()).status(aclModuleParam.getStatus()).build();
        sysAclModule.setOperator(RequestHolder.currentUser().getRealName());
        sysAclModule.setOperatorIp(IpUtils.getUserIP(RequestHolder.currentRequest()));
        sysAclModule.setOperatorTime(LocalDateTime.now());
        SysAclModule module = aclModuleDAO.save(sysAclModule);
        Preconditions.checkNotNull(module, "新增权限模块失败!");
        AclModuleVO vo = CopyUtils.beanCopy(module, new AclModuleVO());
        // 保存日志
        logDAO.save(LogPropertiesUtils.set(LogConstants.TYPE_ACL_MODULE, null, module, module.getId()));
        return new ObjectResponse<>(vo);
    }

    @Override
    public Response update(AclModuleParam aclModuleParam) {
        if (aclModuleParam.getId() == null) {
            return new FailedResponse<>("更新时Id不能为空!");
        }
        SysAclModule befor = aclModuleDAO.findById(aclModuleParam.getId()).orElse(null);
        Preconditions.checkNotNull(befor, "待更新的权限模块不存在");
        SysAclModule after = new SysAclModule();
        BeanUtils.copyProperties(befor, after);
        try {
            CopyUtils.copy(after, aclModuleParam);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        BeanValidator.check(after);
        // 检查该权限模块名称是否存在相同
        if (checkExist(after.getParentId(), after.getName(), after.getId())) {
            throw new RuntimeException("权限模块名称已存在!");
        }
        after.setOperator(RequestHolder.currentUser().getRealName());
        after.setOperatorIp(IpUtils.getUserIP(RequestHolder.currentRequest()));
        after.setOperatorTime(LocalDateTime.now());
        // 将更新后的值进行保存
        SysAclModule save = aclModuleDAO.save(after);
        Preconditions.checkNotNull(save, "更新权限模块失败");
        updateWithChild(befor, save);
        AclModuleVO vo = CopyUtils.beanCopy(save, new AclModuleVO());
        // 保存日志
        logDAO.save(LogPropertiesUtils.set(LogConstants.TYPE_DEPT, befor, save, save.getId()));
        return new ObjectResponse<>(vo);
    }

    @Override
    public Response delete(IdEntity idEntity) {
        BeanValidator.check(idEntity);
        SysAclModule module = aclModuleDAO.findById(idEntity.getId()).orElse(null);
        Preconditions.checkNotNull(module, "数据为空，无法删除");
        // 判断子节点
        List<SysAclModule> allByParentId = aclModuleDAO.findAllByParentId(module.getId());
        if (allByParentId != null && !allByParentId.isEmpty()) {
            throw new RuntimeException("当前模块下面有子模块，无法删除!");
        }
        // 判断是否有权限点
        List<SysAcl> acls = aclService.findAllByModuleId(idEntity.getId());
        if (CollectionUtils.isNotEmpty(acls)) {
            throw new FaileResponseException("模块存在权限点，无法删除!");
        }
        aclModuleDAO.deleteById(idEntity.getId());
        return new SuccessResponse("删除成功");
    }


    /**
     * @param ngPager 分页对象
     * @return com.lnlr.common.response.Response
     * @author: leihfei
     * @description
     * @date: 10:46 2018/10/30
     * @email: leihfein@gmail.com
     */

    @Override
    public NgData page(NgPager ngPager) {
        return new NgData<>(aclModuleDAO.findAll(DynamicSpecifications.bySearchFilter(SysAclModule.class,
                PageUtils.buildSearchFilter(ngPager)), PageUtils.buildPageRequest(ngPager, PageUtils.buildSort(ngPager))), ngPager);
    }

    private String getLevel(Integer aclModuleId) {
        SysAclModule aclModule = aclModuleDAO.findById(aclModuleId).orElse(null);
        if (aclModule == null) {
            return null;
        }
        return aclModule.getLevel();
    }

    @Transactional
    void updateWithChild(SysAclModule before, SysAclModule after) {
        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel();
        if (!after.getLevel().equals(before.getLevel())) {
            // 需要做子菜单更新
            List<SysAclModule> aclModuleList = aclModuleDAO.findAllByLevelLike(before.getLevel() + ".%");
            if (CollectionUtils.isNotEmpty(aclModuleList)) {
                for (SysAclModule dept : aclModuleList) {
                    String level = dept.getLevel();
                    if (level.indexOf(oldLevelPrefix) == 0) {
                        // 算新level
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        dept.setLevel(level);
                    }
                }
                aclModuleDAO.saveAll(aclModuleList);
            }
        }
        aclModuleDAO.save(after);
    }

    /**
     * @param parentId 模块父节点id
     * @param name     模块名称
     * @return boolean
     * @author: leihfei
     * @description
     * @date: 17:06 2018/10/26
     * @email: leihfein@gmail.com
     */
    private boolean checkExist(Integer parentId, String name, Integer id) {
        List<SysAclModule> list = aclModuleDAO.findAllByNameAndParentId(name, parentId);
        if (list != null && !list.isEmpty()) {
            if (list.size() == 1 && id != null && list.get(0).getId() == id) {
                return false;
            }
            return true;
        }
        return false;
    }
}
