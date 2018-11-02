package com.lnlr.security.pojo.master.vo.module;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 23:36 2018/10/28
 * @email:leihfein@gmail.com
 */
@ApiModel(description = "权限模块视图")
@Data
public class AclModuleVO {

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


}
