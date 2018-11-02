package com.lnlr.security.pojo.master.vo.user;

import com.lnlr.security.pojo.master.vo.dept.DeptShortVO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author:leihfei
 * @description 包括部门数据的用户数据
 * @date:Create in 23:46 2018/10/25
 * @email:leihfein@gmail.com
 */
@Data
@ApiModel(description = "包括部门详情的用户数据")
public class UserDeptVO {
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
     * 部门
     */
    private DeptShortVO dept;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;
}