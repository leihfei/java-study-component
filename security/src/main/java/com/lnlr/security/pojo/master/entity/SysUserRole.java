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
 * @description: 角色用户关联表
 * @date:Create in 16:42 2018/10/26
 * @email:leihfein@gmail.com
 */
@Entity
@Table(name = "sys_user_role")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 899375844777880228L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 用户id
     */
    private Integer userId;

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
