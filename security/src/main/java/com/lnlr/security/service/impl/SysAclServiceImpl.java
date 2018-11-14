package com.lnlr.security.service.impl;

import com.google.common.base.Preconditions;
import com.lnlr.common.constains.LogConstants;
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
import com.lnlr.security.pojo.master.dao.SysAclDAO;
import com.lnlr.security.pojo.master.dao.SysLogDAO;
import com.lnlr.security.pojo.master.dto.AclParam;
import com.lnlr.security.pojo.master.entity.SysAcl;
import com.lnlr.security.pojo.master.vo.acl.AclVO;
import com.lnlr.security.service.SysAclService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author:leihfei
 * @description 权限点业务实现
 * @date:Create in 10:47 2018/10/30
 * @email:leihfein@gmail.com
 */
@Service
@Slf4j
public class SysAclServiceImpl implements SysAclService {

    @Autowired
    private SysAclDAO aclDAO;

    @Autowired
    private SysLogDAO logDAO;

    /**
     * @param aclParam 权限点
     * @return com.lnlr.common.response.Response
     * @author: leihfei
     * @description 新增权限点
     * @date: 11:03 2018/10/30
     * @email: leihfein@gmail.com
     */
    @Override
    public Response create(AclParam aclParam) {
        BeanValidator.check(aclParam);
        // 判断权限点是否存在，名称是否重复
        if (checkExist(aclParam.getAclModuleId(), aclParam.getName(), null)) {
            throw new RuntimeException("权限点已存在!");
        }
        // 组装数据
        SysAcl acl = SysAcl.builder().aclModuleId(aclParam.getAclModuleId()).code(generaterCode()).name(aclParam.getName())
                .remark(aclParam.getRemark())
                .seq(aclParam.getSeq())
                .type(aclParam.getType())
                .url(aclParam.getUrl())
                .status(aclParam.getStatus()).build();
        acl.setOperator(RequestHolder.currentUser().getRealName());
        acl.setOperatorIp(IpUtils.getUserIP(RequestHolder.currentRequest()));
        acl.setOperatorTime(LocalDateTime.now());
        SysAcl save = aclDAO.save(acl);
        Preconditions.checkNotNull(save, "新增权限点失败!");
        // 封装返回数据
        AclVO vo = CopyUtils.beanCopy(save, new AclVO());
        // 保存日志
        logDAO.save( LogPropertiesUtils.set(LogConstants.TYPE_ACL, null, save, save.getId()));
        return new ObjectResponse<>(vo);
    }


    @Override
    public Response update(AclParam aclParam) {
        if (aclParam.getId() == null) {
            throw new RuntimeException("更新时Id不能为空!");
        }
        // 查询原始数据
        SysAcl befor = aclDAO.findById(aclParam.getId()).orElse(null);
        Preconditions.checkNotNull(befor, "权限点数据不存在!");
        // 对数据进行备份
        SysAcl after = CopyUtils.beanCopy(befor, new SysAcl());
        // 把数据按照属性进行赋值
        try {
            CopyUtils.copy(after, aclParam);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        // 校验是否合法
        BeanValidator.check(after);
        // 判断权限点是否存在，名称是否重复
        if (checkExist(aclParam.getAclModuleId(), aclParam.getName(), aclParam.getId())) {
            throw new RuntimeException("权限点已存在!");
        }
        // 合法之后进行数据的保存
        after.setOperator(RequestHolder.currentUser().getRealName());
        after.setOperatorIp(IpUtils.getUserIP(RequestHolder.currentRequest()));
        after.setOperatorTime(LocalDateTime.now());
        SysAcl save = aclDAO.save(after);
        Preconditions.checkNotNull(save, "更新权限点失败!");
        // 封装返回数据
        AclVO vo = CopyUtils.beanCopy(save, new AclVO());
        // 保存日志
        logDAO.save( LogPropertiesUtils.set(LogConstants.TYPE_ACL, befor, save, after.getId()));
        return new ObjectResponse<>(vo);
    }

    @Override
    public void delete(IdEntity idEntity) {
        BeanValidator.check(idEntity);
        aclDAO.deleteById(idEntity.getId());
    }

    @Override
    public NgData page(NgPager ngPager) {
        return new NgData<>(aclDAO.findAll(DynamicSpecifications.bySearchFilter(SysAcl.class,
                PageUtils.buildSearchFilter(ngPager)), PageUtils.buildPageRequest(ngPager, PageUtils.buildSort(ngPager))), ngPager);
    }

    @Override
    public List<SysAcl> findAll() {
        return aclDAO.findAll();
    }

    /**
     * @param ids 权限Id
     * @return java.util.List<com.lnlr.security.pojo.master.entity.SysAcl>
     * @author: leihfei
     * @description 通过Id查询list
     * @date: 1:04 2018/11/1
     * @email: leihfein@gmail.com
     */
    @Override
    public List<SysAcl> findAllByIds(List<Integer> ids) {
        return aclDAO.findAllById(ids);
    }

    @Override
    public List<SysAcl> findAllByModuleId(Integer id) {
        return aclDAO.findAllByAclModuleId(id);
    }

    /**
     * 通过url查询权限点
     *
     * @param url
     * @return
     */
    @Override
    public List<SysAcl> findAllByUrl(String url) {
        return aclDAO.findAllByUrl(url);
    }

    /**
     * @param
     * @return java.lang.String
     * @author: leihfei
     * @description 生成权限点代码
     * @date: 11:12 2018/10/30
     * @email: leihfein@gmail.com
     */
    private String generaterCode() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date()) + "_" + (int) (Math.random() * 100);
    }

    /**
     * @param aclModuleId 权限模块id,
     * @param name        权限点名称
     * @param id          id
     * @return boolean
     * @author: leihfei
     * @description 检查权限点在该权限模块下是否存在，存在返回true,否则返回false
     * @date: 11:07 2018/10/30
     * @email: leihfein@gmail.com
     */

    private boolean checkExist(Integer aclModuleId, String name, Integer id) {
        List<SysAcl> aces = aclDAO.findAllByAclModuleIdAndName(aclModuleId, name);
        if ((aces != null) && !aces.isEmpty()) {
            if (aces.size() == 1 && id != null && aces.get(0).getId() == id) {
                return false;
            }
            return true;
        }
        return false;
    }
}
