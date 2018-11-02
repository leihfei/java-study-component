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
 * @description: 权限模块参数
 * @date:Create in 16:25 2018/10/26
 * @email:leihfein@gmail.com
 */
@ApiModel(description = "权限模块实体类")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AclModuleParam {

    private Integer id;

    /**
     * 权限模块名称
     */
    @ApiModelProperty(name = "权限模块名称")
    @NotBlank(message = "权限模块名称不能为空")
    @Length(min = 2, max = 20, message = "权限名称长度必须在2~20字符之间")
    private String name;

    /**
     * 权限模块父id
     */
    @ApiModelProperty(name = "权限模块父模块")
    private Integer parentId = 0;


    /**
     * 权限模块在当前层级下的顺序，由小到大
     */
    @NotNull(message = "权限模块展示顺序不能为空")
    @ApiModelProperty(name = "权限模块顺序")
    private Integer seq;

    /**
     * 模块状态：1-可用，0-冻结
     */
    @NotNull(message = "权限状态不能为空")
    @ApiModelProperty(name = "权限状态")
    @Min(value = 0, message = "状态不合法")
    @Max(value = 1, message = "状态不合法")
    private Integer status;

    /**
     * 备注
     */
    @Length(max = 200, message = "200")
    private String remark;
}
