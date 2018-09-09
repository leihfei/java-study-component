package com.lnlr.authority.security.pojo.master.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author:leihfei
 * @description 用户实体类
 * @date:Create in 19:41 2018/8/30
 * @email:leihfein@gmail.com
 */
@Entity
@Table(name = "sys_user")
@Data
@Builder
public class SysUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 系统登录用户名称
     */
    private String username;

    /**
     * 系统用户名称
     */
    private String realName;

    /**
     * 用户电话号码
     */
    private String telphone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 密码盐值
     */
    private String salt;

    /**
     * 部门id
     */
    private String deptId;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作时间
     */
    private LocalDateTime operatorTime;

    /**
     * 操作ip
     */
    private String operatorIp;

}
