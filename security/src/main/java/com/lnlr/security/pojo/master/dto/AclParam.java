package com.lnlr.security.pojo.master.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 0:05 2018/10/29
 * @email:leihfein@gmail.com
 */
@ApiModel(description = "权限点实体类")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AclParam {
    /**
     * 权限id
     */
    private Integer id;

    /**
     * 权限名称
     */
    @NotBlank(message = "权限点名称不可以为空")
    @Length(min = 2, max = 20, message = "权限点名称2-20字符之间")
    @ApiModelProperty(value = "权限点名称")
    private String name;

    /**
     * 权限所在的模块id
     */
    @NotNull(message = "必须指定权限模块")
    @ApiModelProperty(value = "权限模块Id")
    private Integer aclModuleId;

    /**
     * 请求url地址，可以填入正则表达式,
     */
    @Length(min = 6, max = 100, message = "权限点URL必须在6-100字符之间")
    @ApiModelProperty(value = "权限点URL地址")
    private String url;

    /**
     * 类型：1-菜单，2-按钮,3-其他
     */
    @NotNull(message = "必须制定权限点类型")
    @Min(value = 1, message = "权限点类型不合法")
    @Max(value = 3, message = "权限点类型不合法")
    @ApiModelProperty(value = "权限点类型")
    private Integer type;


    /**
     * 权限模块在当前层级下的顺序，由小到大
     */
    @NotNull(message = "权限点必须有序")
    @ApiModelProperty(value = "权限点顺序")
    private Integer seq;

    /**
     * 权限状态：1-正常，0-冻结
     */
    @NotNull(message = "状态不允许为空")
    @Min(value = 0, message = "权限点状态不合法")
    @Max(value = 1, message = "权限点状态不合法")
    @ApiModelProperty(value = "权限点状态")
    private Integer status;

    @Length(min = 2, max = 100, message = "备注在2-100字符之间")
    @ApiModelProperty(value = "权限点备注")
    private String remark;
}
