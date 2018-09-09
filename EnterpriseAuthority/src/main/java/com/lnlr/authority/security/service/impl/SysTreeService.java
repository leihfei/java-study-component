package com.lnlr.authority.security.service.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.lnlr.authority.security.pojo.master.dao.SysDeptDAO;
import com.lnlr.authority.security.pojo.master.dto.DeptLevelTree;
import com.lnlr.authority.security.pojo.master.entity.SysDept;
import com.lnlr.common.utils.LevelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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


    /**
     * @param
     * @return java.util.List<com.lnlr.authority.security.pojo.master.dto.DeptLevelTree>
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
        Collections.sort(rootList, new Comparator<DeptLevelTree>() {
            @Override
            public int compare(DeptLevelTree o1, DeptLevelTree o2) {
                return o1.getSeq() - o2.getSeq();
            }
        });
        // 需要把当前排序的值进行递归排序
        transformDeptTree(rootList, LevelUtils.ROOT, levelDeptMap);
        return rootList;
    }

    /**
     * @param deptLevelTreeList
     * @param level
     * @param levelDeptMap
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

    public Comparator<DeptLevelTree> deptSeqComparator = new Comparator<DeptLevelTree>() {
        @Override
        public int compare(DeptLevelTree o1, DeptLevelTree o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };
}
