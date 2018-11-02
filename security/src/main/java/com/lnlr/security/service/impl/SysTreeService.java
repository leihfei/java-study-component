package com.lnlr.security.service.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.lnlr.common.entity.IdEntity;
import com.lnlr.common.utils.LevelUtils;
import com.lnlr.security.pojo.master.dao.SysAclModuleDAO;
import com.lnlr.security.pojo.master.dao.SysDeptDAO;
import com.lnlr.security.pojo.master.dto.AclDTO;
import com.lnlr.security.pojo.master.dto.DeptLevelTree;
import com.lnlr.security.pojo.master.dto.ModuleLevelTree;
import com.lnlr.security.pojo.master.entity.SysAcl;
import com.lnlr.security.pojo.master.entity.SysAclModule;
import com.lnlr.security.pojo.master.entity.SysDept;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author:leihfei
 * @description 树形结构
 * @date:Create in 22:56 2018/9/3
 * @email:leihfein@gmail.com
 */
@Service
public class SysTreeService {

    @Autowired
    private SysDeptDAO deptDAO;

    @Autowired
    private SysAclModuleDAO aclModuleDAO;

    @Autowired
    private SysCoreService coreService;


    /**
     * @param
     * @return java.util.List<DeptLevelTree>
     * @author: leihfei
     * @description 获取部门树形结构
     * @date: 22:57 2018/9/3
     * @email: leihfein@gmail.com
     */
    public List<DeptLevelTree> deptTree() {
        List<SysDept> depts = deptDAO.findAll();
        List<DeptLevelTree> trees = Lists.newArrayList();
        for (SysDept dept : depts) {
            DeptLevelTree tree = DeptLevelTree.adapt(dept);
            trees.add(tree);
        }
        return deptListToTree(trees);
    }

    /**
     * @param
     * @return java.util.List<com.lnlr.security.pojo.master.dto.ModuleLevelTree>
     * @author: leihfei
     * @description 获取权限菜单树
     * @date: 22:40 2018/10/29
     * @email: leihfein@gmail.com
     */
    public List<ModuleLevelTree> moduleTree() {
        List<SysAclModule> modules = aclModuleDAO.findAll();
        List<ModuleLevelTree> trees = Lists.newArrayList();
        for (SysAclModule module : modules) {
            ModuleLevelTree tree = ModuleLevelTree.adapt(module);
            trees.add(tree);
        }
        return moduleListToTree(trees);
    }

    /**
     * @param trees 菜单列表
     * @return java.util.List<com.lnlr.security.pojo.master.dto.ModuleLevelTree>
     * @author: leihfei
     * @description 将菜单列表转成树形结构
     * @date: 22:41 2018/10/29
     * @email: leihfein@gmail.com
     */
    private List<ModuleLevelTree> moduleListToTree(List<ModuleLevelTree> trees) {
        if (CollectionUtils.isEmpty(trees)) {
            return Lists.newArrayList();
        }
        // 有数据，进行处理数据
        Multimap<String, ModuleLevelTree> levelTreeMultimap = ArrayListMultimap.create();
        // 保存根节点
        List<ModuleLevelTree> rootList = Lists.newArrayList();
        for (ModuleLevelTree levelTree : trees) {
            levelTreeMultimap.put(levelTree.getLevel(), levelTree);
            if (LevelUtils.ROOT.equals(levelTree.getLevel())) {
                rootList.add(levelTree);
            }
        }
        // 对所有节点进行排序
        Collections.sort(rootList, moduleLevelTreeComparable);
        // 处理子节点
        transformModuleTree(rootList, LevelUtils.ROOT, levelTreeMultimap);
        return rootList;
    }

    /**
     * @param moduleLevelList 所有的菜单list
     * @param level           当前的级别
     * @param maps            封装了级别的map对象数组
     * @return void
     * @author: leihfei
     * @description 将list数组对象转化为树形结构对象
     * @date: 23:54 2018/10/29
     * @email: leihfein@gmail.com
     */
    public void transformModuleTree(List<ModuleLevelTree> moduleLevelList, String level, Multimap<String, ModuleLevelTree> maps) {
        for (int i = 0; i < moduleLevelList.size(); i++) {
            ModuleLevelTree levelTree = moduleLevelList.get(i);
            // 处理当前层级
            String nextLevel = LevelUtils.calculatelevel(level, levelTree.getId());
            // 得到下一层的数据
            List<ModuleLevelTree> moduleLevelTrees = (List<ModuleLevelTree>) maps.get(nextLevel);
            // 循环子节点，得到节点数据
            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(moduleLevelTrees)) {
                Collections.sort(moduleLevelTrees, moduleLevelTreeComparable);
                // 设置子节点
                levelTree.setChildren(moduleLevelTrees);
                transformModuleTree(moduleLevelTrees, level, maps);
            }
        }
    }

    public List<DeptLevelTree> deptListToTree(List<DeptLevelTree> deptLevelList) {
        if (CollectionUtils.isEmpty(deptLevelList)) {
            return Lists.newArrayList();
        }
        // 进行处理
        Multimap<String, DeptLevelTree> levelDeptMap = ArrayListMultimap.create();
        // 取出root第一级部门
        List<DeptLevelTree> rootList = Lists.newArrayList();
        // 循环deptLevelList
        for (DeptLevelTree levelTree : deptLevelList) {
            levelDeptMap.put(levelTree.getLevel(), levelTree);
            if (LevelUtils.ROOT.equals(levelTree.getLevel())) {
                // 表名是根节点
                rootList.add(levelTree);
            }
        }
        // 对root进行排序,按照seq从小到大排序
        Collections.sort(rootList, deptSeqComparator);
        // 需要把当前排序的值进行递归排序
        transformDeptTree(rootList, LevelUtils.ROOT, levelDeptMap);
        return rootList;
    }

    /**
     * @param deptLevelTreeList 根节点部门
     * @param level             根节点等级
     * @param levelDeptMap      每个节点数据
     * @return void
     * @author: leihfei
     * @description
     * @date: 23:30 2018/9/3
     * @email: leihfein@gmail.com
     */
    public void transformDeptTree(List<DeptLevelTree> deptLevelTreeList, String level, Multimap<String, DeptLevelTree> levelDeptMap) {
        for (int i = 0; i < deptLevelTreeList.size(); i++) {
            // 遍历该层元素
            DeptLevelTree levelTree = deptLevelTreeList.get(i);
            // 处理当前层级
            String nextLevel = LevelUtils.calculatelevel(level, levelTree.getId());
            // 处理下一层
            List<DeptLevelTree> tempDeptList = (List<DeptLevelTree>) levelDeptMap.get(nextLevel);
            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(tempDeptList)) {
                // 排序
                Collections.sort(tempDeptList, deptSeqComparator);
                // 设置下一层部门
                levelTree.setChildren(tempDeptList);
                // 进入下一层处理
                transformDeptTree(tempDeptList, level, levelDeptMap);
            }
        }
    }


    /**
     * @param idEntity 角色Id
     * @return java.util.List
     * @author: leihfei
     * @description 通过角色id查询模块权限树
     * @date: 0:43 2018/11/1
     * @email: leihfein@gmail.com
     */
    public List<ModuleLevelTree> roleTree(IdEntity idEntity) {
        if (idEntity == null) {
            throw new RuntimeException("角色数据异常!");
        }
        // 1、当前用户已分配的权限点
        List<SysAcl> userAclList = coreService.getCurrentUserAclList();
        // 2. 当前角色分配的权限点
        List<SysAcl> roleAclList = coreService.getRoleAclList(idEntity.getId());
        // 3. 当前系统所有权限点
        List<SysAcl> aclAll = coreService.getAclAll();
        List<AclDTO> aclDTOS = Lists.newArrayList();
        // 得到已分配的权限点
        Set<Integer> userAclIdSet = userAclList.stream().map(e -> e.getId()).collect(Collectors.toSet());
        // 得到该角色的所有权限点
        Set<Integer> roleAclIdSet = roleAclList.stream().map(e -> e.getId()).collect(Collectors.toSet());
        // 匹配得到每个权限点的功能，是否被分配，是否勾选
        for (SysAcl acl : aclAll) {
            AclDTO aclDTO = AclDTO.adapt(acl);
            if (userAclIdSet.contains(aclDTO.getId())) {
                aclDTO.setHasAcl(true);
            }
            if (roleAclIdSet.contains(aclDTO.getId())) {
                aclDTO.setChecked(true);
            }
            aclDTOS.add(aclDTO);
        }
        return aclListToTree(aclDTOS);
    }

    /**
     * @param aclDTOS 权限点集合
     * @return java.util.List<com.lnlr.security.pojo.master.dto.AclModuleLevelDTO>
     * @author: leihfei
     * @description j将所有的权限点list->转为tree结构
     * @date: 1:27 2018/11/1
     * @email: leihfein@gmail.com
     */
    private List<ModuleLevelTree> aclListToTree(List<AclDTO> aclDTOS) {
        if (CollectionUtils.isEmpty(aclDTOS)) {
            return Lists.newArrayList();
        }
        List<ModuleLevelTree> moduleLevelTrees = moduleTree();
        Multimap<Integer, AclDTO> moduleIdAclMap = ArrayListMultimap.create();
        for (AclDTO aclDTO : aclDTOS) {
            if (aclDTO.getStatus() == 1) {
                moduleIdAclMap.put(aclDTO.getAclModuleId(), aclDTO);
            }
        }
        // 将权限绑定到模块上
        bindAclsWithOrder(moduleLevelTrees, moduleIdAclMap);
        return moduleLevelTrees;
    }

    /**
     * @param moduleLevelTrees 模块树
     * @param moduleIdAclMap   模块id和模块的map-list集合
     * @return java.util.List<com.lnlr.security.pojo.master.dto.ModuleLevelTree>
     * @author: leihfei
     * @description 绑定权限到模块树上
     * @date: 1:35 2018/11/1
     * @email: leihfein@gmail.com
     */
    private void bindAclsWithOrder(List<ModuleLevelTree> moduleLevelTrees, Multimap<Integer, AclDTO> moduleIdAclMap) {
        if (CollectionUtils.isEmpty(moduleLevelTrees)) {
            return;
        }
        for (ModuleLevelTree treeNode : moduleLevelTrees) {
            List<AclDTO> aclDTOS = (List<AclDTO>) moduleIdAclMap.get(treeNode.getId());
            if (CollectionUtils.isNotEmpty(aclDTOS)) {
                // 有数据
                Collections.sort(aclDTOS, aclSeqComparator);
                treeNode.setAclList(aclDTOS);
            }
            bindAclsWithOrder(treeNode.getChildren(), moduleIdAclMap);
        }
    }


    /**
     * 部门比较器
     */
    public Comparator<DeptLevelTree> deptSeqComparator = new Comparator<DeptLevelTree>() {
        @Override
        public int compare(DeptLevelTree o1, DeptLevelTree o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };

    /**
     * 模块菜单比较器
     */
    public Comparator<ModuleLevelTree> moduleLevelTreeComparable = new Comparator<ModuleLevelTree>() {
        @Override
        public int compare(ModuleLevelTree o1, ModuleLevelTree o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };


    public Comparator<AclDTO> aclSeqComparator = new Comparator<AclDTO>() {
        public int compare(AclDTO o1, AclDTO o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };


}
