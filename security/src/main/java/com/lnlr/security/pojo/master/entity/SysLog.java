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
 * @description: 系统日志
 * @date:Create in 16:36 2018/10/26
 * @email:leihfein@gmail.com
 */
@Entity
@Table(name = "sys_log")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysLog implements Serializable {
    private static final long serialVersionUID = -5936397599128991259L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 权限更新类型：1-部门，2-用户，3-权限模块，4-权限，5-角色，6-角色用户关系，7-角色权限关系
     */
    private Integer type;

    /**
     * 基于type后指定的对象id,比如用户，权限，角色等表的主键
     */
    private Integer targetId;

    /**
     * 基于target_id之后对象的原始数据
     */
    private String oldValue;

    /**
     * 基于target_id之后对象的更新数据
     */
    private String newValue;

    /**
     * 当前是否复原过，0-没有，1-复原过
     */
    private Integer status;

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
