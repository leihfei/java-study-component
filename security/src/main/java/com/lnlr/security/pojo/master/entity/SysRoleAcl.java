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
 * @description: 角色权限关联表
 * @date:Create in 16:41 2018/10/26
 * @email:leihfein@gmail.com
 */
@Entity
@Table(name = "sys_role_acl")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleAcl implements Serializable {

    private static final long serialVersionUID = 644145337601564373L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 权限id
     */
    private Integer aclId;
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
