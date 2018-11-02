package com.lnlr.security.pojo.master.vo.acl;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author:leihfei
 * @description: 权限点返回数据视图
 * @date:Create in 11:14 2018/10/30
 * @email:leihfein@gmail.com
 */
@ApiModel(description = "权限点返回数据视图")
@Data
public class AclVO {
    /**
     * 权限id
     */
    private Integer id;

    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限点名称")
    private String name;

    /**
     * 权限所在的模块id
     */
    @ApiModelProperty(value = "权限模块Id")
    private Integer aclModuleId;

    /**
     * 请求url地址，可以填入正则表达式,
     */
    @ApiModelProperty(value = "权限点URL地址")
    private String url;

    /**
     * 类型：1-菜单，2-按钮,3-其他
     */
    @ApiModelProperty(value = "权限点类型")
    private Integer type;


    /**
     * 权限模块在当前层级下的顺序，由小到大
     */
    @ApiModelProperty(value = "权限点顺序")
    private Integer seq;

    /**
     * 权限状态：1-正常，0-冻结
     */
    @ApiModelProperty(value = "权限点状态")
    private Integer status;

    @ApiModelProperty(value = "权限点备注")
    private String remark;

    @ApiModelProperty(value = "权限点代码")
    private String code;
}
