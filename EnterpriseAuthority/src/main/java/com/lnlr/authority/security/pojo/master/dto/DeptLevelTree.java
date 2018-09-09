package com.lnlr.authority.security.pojo.master.dto;

import com.google.common.collect.Lists;
import com.lnlr.authority.security.pojo.master.entity.SysDept;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author:leihfei
 * @description 部门层级树形结构
 * @date:Create in 22:53 2018/9/3
 * @email:leihfein@gmail.com
 */
@Data
public class DeptLevelTree extends SysDept {

    private List<DeptLevelTree> children = Lists.newArrayList();

    /**
     * @param sysDept 部门类数据
     * @author: leihfei
     * @description 适配一个dept类，得到一个树形结构
     * @return com.lnlr.authority.security.pojo.master.dto.DeptLevelTree
     * @date: 22:54 2018/9/3
     * @email: leihfein@gmail.com
     */
    public static DeptLevelTree adapt(SysDept sysDept) {
        DeptLevelTree tree = new DeptLevelTree();
        BeanUtils.copyProperties(sysDept, tree);
        return tree;
    }
}
