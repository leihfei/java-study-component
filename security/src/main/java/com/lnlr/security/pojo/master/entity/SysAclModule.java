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
 * @description:
 * @date:Create in 16:27 2018/10/26
 * @email:leihfein@gmail.com
 */
@Entity
@Table(name = "sys_acl_module")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysAclModule implements Serializable {

    private static final long serialVersionUID = 3417746447000201028L;
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 权限模块名称
     */
    private String name;

    /**
     * 权限模块父id
     */
    private Integer parentId;

    /**
     * 权限模块层级
     */
    private String level;

    /**
     * 权限模块在当前层级下的顺序，由小到大
     */
    private Integer seq;

    /**
     * 模块状态：1-可用，0-冻结
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
