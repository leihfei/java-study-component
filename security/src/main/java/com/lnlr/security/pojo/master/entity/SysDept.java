package com.lnlr.security.pojo.master.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author:leihfei
 * @description 权限管理系统部门实体类
 * @date:Create in 22:23 2018/9/3
 * @email:leihfein@gmail.com
 */
@Entity
@Table(name = "sys_dept")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysDept implements Serializable {
    private static final long serialVersionUID = 1294961354861256270L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
     * 操作用户
     */
    private String operator;

    /**
     * 操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operatorTime;

    /**
     * id地址
     */
    private String operatorIp;


}
