package com.lnlr.security.pojo.master.dto;

import com.google.common.collect.Lists;
import com.lnlr.security.pojo.master.entity.SysAclModule;
import com.lnlr.security.pojo.master.entity.SysDept;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author:leihfei
 * @description 权限层级树形结构
 * @date:Create in 22:53 2018/9/3
 * @email:leihfein@gmail.com
 */
@Data
@EqualsAndHashCode
public class ModuleLevelTree extends SysAclModule {

    private List<ModuleLevelTree> children = Lists.newArrayList();

    private List<AclDTO> aclList = Lists.newArrayList();

    /**
     * @param aclModule 权限菜单类数据
     * @return DeptLevelTree
     * @author: leihfei
     * @description 适配一个权限类，得到一个树形结构
     * @date: 22:54 2018/9/3
     * @email: leihfein@gmail.com
     */
    public static ModuleLevelTree adapt(SysAclModule aclModule) {
        ModuleLevelTree tree = new ModuleLevelTree();
        BeanUtils.copyProperties(aclModule, tree);
        return tree;
    }
}
