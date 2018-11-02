package com.lnlr.security.pojo.master.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author:leihfei
 * @description shiro拦截器实体类
 * @date:Create in 9:22 2018/10/9
 * @email:leihfein@gmail.com
 */
@Data
@Table(name = "sys_default_filter")
@Entity
public class SysDefaultFilter implements Serializable {
    private static final long serialVersionUID = 7916151847033920045L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * url地址
     */
    private String url;

    /**
     * 过滤器名称
     */
    private String filterName;

    /**
     * 过滤器顺序
     */
    private Integer sort;
}
