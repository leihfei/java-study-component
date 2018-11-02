package com.lnlr.security.pojo.master.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author:leihfei
 * @description: 角色参数
 * @date:Create in 16:26 2018/10/30
 * @email:leihfein@gmail.com
 */
@ApiModel(description = "角色参数", value = "角色管理参数")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoleParam {

    private Integer id;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    @Length(min = 2, max = 20, message = "角色名称需在2-20字符之间")
    private String name;

    /**
     * 角色类型，1-管理员角色，2-其他,3-超级管理员
     */
    @Min(value = 1, message = "角色类型不合法")
    @Max(value = 3, message = "角色类型不合法")
    private Integer type = 1;

    /**
     * 角色状态：1-可用，0-冻结
     */
    @NotNull(message = "角色状态不允许为空")
    @Min(value = 0, message = "角色状态不合法")
    @Max(value = 1, message = "角色状态不合法")
    private Integer status;

    /**
     * 备注
     */
    @Length(min = 2, max = 200, message = "备注长度在2-200之间")
    private String remark;

}
