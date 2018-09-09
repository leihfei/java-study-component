package com.lnlr.authority.security.service.impl;

import com.google.common.base.Preconditions;
import com.lnlr.authority.security.pojo.master.dao.SysDeptDAO;
import com.lnlr.authority.security.pojo.master.dto.DeptDTO;
import com.lnlr.authority.security.pojo.master.entity.SysDept;
import com.lnlr.authority.security.pojo.master.vo.DeptVO;
import com.lnlr.authority.security.service.SysDeptService;
import com.lnlr.common.entity.IdEntity;
import com.lnlr.common.exception.FaileResponseException;
import com.lnlr.common.exception.ParamException;
import com.lnlr.common.jpa.model.NgData;
import com.lnlr.common.jpa.model.NgPager;
import com.lnlr.common.jpa.query.DynamicSpecifications;
import com.lnlr.common.jpa.query.PageUtils;
import com.lnlr.common.response.ObjectResponse;
import com.lnlr.common.response.Response;
import com.lnlr.common.response.SuccessResponse;
import com.lnlr.common.utils.BeanValidator;
import com.lnlr.common.utils.LevelUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author:leihfei
 * @description 部门管理实现
 * @date:Create in 22:29 2018/9/3
 * @email:leihfein@gmail.com
 */
@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired
    private SysDeptDAO deptDAO;


    public NgData page(NgPager ngPager) {
        NgData<SysDept> pageData = new NgData<>(deptDAO.findAll(DynamicSpecifications.bySearchFilter(SysDept.class,
                PageUtils.buildSearchFilter(ngPager)), PageUtils.buildPageRequest(ngPager, PageUtils.buildSort(ngPager))), ngPager);
        return pageData;
    }


    /**
     * @param deptDTO 部门传输对象
     * @return com.lnlr.common.response.Response
     * @author: leihfei
     * @description 新增部门
     * @date: 22:31 2018/9/3
     * @email: leihfein@gmail.com
     */
    @Override
    public Response create(DeptDTO deptDTO) {
        BeanValidator.check(deptDTO);
        if (checkExist(deptDTO.getParentId(), deptDTO.getName(), deptDTO.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        SysDept dept = SysDept.builder().name(deptDTO.getName()).parentId(deptDTO.getParentId())
                .seq(deptDTO.getSeq()).remark(deptDTO.getRemark()).build();
        dept.setLevel(LevelUtils.calculatelevel(getLevel(dept.getParentId()), deptDTO.getParentId()));
        dept.setOperator("system"); // TODO
        dept.setOperatorIp("127.0.0.1");// TODO
        dept.setOperatorTime(LocalDateTime.now());
        SysDept save = deptDAO.save(dept);
        DeptVO vo = new DeptVO();
        if (save != null) {
            // 组装vo返回
            BeanUtils.copyProperties(save, vo);
        }
        return new ObjectResponse<>(vo);
    }

    /**
     * 更新部门
     *
     * @param deptDTO
     * @return
     */
    @Override
    public Response update(DeptDTO deptDTO) {
        BeanValidator.check(deptDTO);
        if (checkExist(deptDTO.getParentId(), deptDTO.getName(), deptDTO.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        SysDept sysDept = deptDAO.findById(deptDTO.getId()).orElse(null);
        Preconditions.checkNotNull(sysDept, "待更新的部门不存在");
        if (checkExist(deptDTO.getParentId(), deptDTO.getName(), deptDTO.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        SysDept after = SysDept.builder().id(deptDTO.getId()).name(deptDTO.getName()).parentId(deptDTO.getParentId())
                .seq(deptDTO.getSeq()).remark(deptDTO.getRemark()).build();
        after.setLevel(LevelUtils.calculatelevel(getLevel(deptDTO.getParentId()), deptDTO.getParentId()));
        after.setOperatorTime(LocalDateTime.now());
        after.setOperator("system");
        after.setOperatorIp("127.0.0.1");
        updateWithChild(sysDept, after);
        DeptVO vo = new DeptVO();
        // 组装vo返回
        BeanUtils.copyProperties(after, vo);
        return new ObjectResponse<>(vo);
    }

    @Transactional
    void updateWithChild(SysDept before, SysDept after) {
        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel();
        if (!after.getLevel().equals(before.getLevel())) {
            // 需要做子部门更新
            List<SysDept> deptList = deptDAO.findAllByLevelLike(before.getLevel() + ".%");
            if (CollectionUtils.isNotEmpty(deptList)) {
                for (SysDept dept : deptList) {
                    String level = dept.getLevel();
                    if (level.indexOf(oldLevelPrefix) == 0) {
                        // 算新level
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        dept.setLevel(level);
                    }
                }
                deptDAO.saveAll(deptList);
            }
        }
        deptDAO.save(after);
    }

    private String getLevel(Integer deptId) {
        SysDept dept = deptDAO.findById(deptId).orElse(null);
        if (dept == null) {
            return null;
        }
        return dept.getLevel();
    }

    private boolean checkExist(Integer parentId, String deptName, Integer deptId) {
        List<SysDept> child = deptDAO.findAllByNameAndParentId(deptName, parentId);
        if (CollectionUtils.isNotEmpty(child)) {
            if (deptId != null && child.size() == 1 && child.get(0).getId() == deptId) {
                // 还需要处理deptId存在的情况
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * 删除部门
     *
     * @param id
     * @return
     */
    @Override
    public Response delete(IdEntity id) {
        BeanValidator.check(id);
        // 查询数据是否存在
        SysDept dept = deptDAO.findById(id.getId()).orElse(null);
        if (dept == null) {
            throw new FaileResponseException("部门信息不存在");
        }
        // 检查子部门，用户
        List<SysDept> children = deptDAO.findAllByParentId(dept.getId());
        if (CollectionUtils.isNotEmpty(children)) {
            throw new FaileResponseException("该部门存在子部门");
        }
        // 检查用户数据
        //TODO
        return new SuccessResponse("删除成功");
    }
}
