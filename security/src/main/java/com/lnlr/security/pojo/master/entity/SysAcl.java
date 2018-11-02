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
 * @description 权限点
 * @date:Create in 16:28 2018/10/26
 * @email:leihfein@gmail.com
 */
@Entity
@Table(name = "sys_acl")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysAcl implements Serializable {
    private static final long serialVersionUID = 7048305294973392111L;
    /**
     * 权限id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 权限码
     */
    private String code;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限所在的模块id
     */
    private Integer aclModuleId;

    /**
     * 请求url地址，可以填入正则表达式,
     */
    private String url;

    /**
     * 类型：1-菜单，2-按钮,3-其他
     */
    private Integer type;


    /**
     * 权限模块在当前层级下的顺序，由小到大
     */
    private Integer seq;

    /**
     * 权限状态：1-正常，0-冻结
     */
    private Integer status;

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
     * 操作ip
     */
    private String operatorIp;
}
