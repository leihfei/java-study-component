package com.lnlr.security.pojo.master.vo.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author:leihfei
 * @description: 角色视图
 * @date:Create in 16:58 2018/10/30
 * @email:leihfein@gmail.com
 */
@ApiModel(description = "角色返回数据视图")
@Data
public class RoleVO {
    private Integer id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String name;

    /**
     * 角色类型，1-管理员角色，2-其他
     */
    @ApiModelProperty(value = "角色类型", example = "1-管理员角色，2-其他")
    private Integer type = 1;

    /**
     * 角色状态：1-可用，0-冻结
     */
    @ApiModelProperty(value = "角色状态", example = "1-可用,0-冻结")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "角色备注")
    private String remark;

}
