package com.lnlr.authority.security.pojo.master.vo;

import lombok.Data;

import javax.persistence.Id;

/**
 * @author:leihfei
 * @description 部门视图显示对象
 * @date:Create in 22:23 2018/9/3
 * @email:leihfein@gmail.com
 */
@Data
public class DeptVO {

    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 父级部门
     */
    private Integer parentId;

    /**
     * 部门层级关系
     */
    private String level;

    /**
     * 部门排序等级
     */
    private Integer seq;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作者
     */
    private String operator;
}
