package com.lnlr.security.pojo.master.vo.user;

import lombok.Data;

/**
 * @author:leihfei
 * @description 系统用户返回视图
 * @date:Create in 17:05 2018/9/6
 * @email:leihfein@gmail.com
 */
@Data
public class UserVO {

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
}
