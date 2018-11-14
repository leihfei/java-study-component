package com.lnlr.security.pojo.master.dto;

import com.lnlr.common.entity.IdEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author:leihfei
 * @description: 角色-权限更改操作对象
 * @date:Create in 20:16 2018/11/7
 * @email:leihfein@gmail.com
 */
@Data
@ApiModel(description = "更新角色-权限点对象")
public class RoleAclOperatorDTO extends IdEntity {

    @ApiModelProperty(value = "权限点列表")
    private List<Integer> aclIds;
}
