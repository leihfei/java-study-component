package com.lnlr.security.pojo.master.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author:leihfei
 * @description 用户传输对象
 * @date:Create in 15:56 2018/9/6
 * @email:leihfein@gmail.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户传输对象")
public class UserParam {

    private Integer id;

    /**
     * 系统登录用户名称
     */
    @NotBlank(message = "登陆名称不能为空")
    @Length(min = 1, max = 20, message = "登陆名称长度必须20字以内")
    private String username;

    /**
     * 系统用户名称
     */
    @NotBlank(message = "用户名不能为空")
    @Length(min = 1, max = 20, message = "用户名长度必须20字以内")
    private String realName;

    /**
     * 用户电话号码
     */
    @NotBlank(message = "电话号码不能为空")
    @Length(min = 1, max = 13, message = "电话号码长度必须13字以内")
    private String telphone;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Length(min = 1, max = 50, message = "邮箱长度必须50字以内")
    private String email;

    /**
     * 部门id
     */
    @NotNull(message = "必须提供用户所在部门")
    private String deptId;

    /**
     * 状态
     */
    @NotNull(message = "必须指定用户状态")
    @Min(value = 0, message = "用户状态不合法")
    @Max(value = 2, message = "用户状态不合法")
    private Integer status;

    /**
     * 备注
     */
    @Length(min = 0, max = 200, message = "备注不能超过200字")
    private String remark;

}
