package com.lnlr.authority.security.pojo.master.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
public class User {
    @Id
    private Integer id;

    private String username;

    private String realName;

    private String telphone;

    private String email;

    private String password;

    private String salt;

    private String deptId;

    private Integer status;

    private String remark;

    private String operator;

    private LocalDateTime operatorTime;

    private String operatorIp;

}
