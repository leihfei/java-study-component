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
 * @description 系统角色
 * @date:Create in 16:40 2018/10/26
 * @email:leihfein@gmail.com
 */
@Entity
@Table(name = "sys_role")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysRole implements Serializable {


    private static final long serialVersionUID = 5833681258296737466L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色类型，1-管理员角色，2-其他
     */
    private Integer type;

    /**
     * 角色状态：1-可用，0-冻结
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
