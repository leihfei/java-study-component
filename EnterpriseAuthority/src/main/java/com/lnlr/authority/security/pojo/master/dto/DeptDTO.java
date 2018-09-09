package com.lnlr.authority.security.pojo.master.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author:leihfei
 * @description 部门传输对象
 * @date:Create in 22:22 2018/9/3
 * @email:leihfein@gmail.com
 */
@Data
public class DeptDTO {

    private Integer id;

    /**
     * 部门名称
     */
    @NotBlank(message = "部门名称不能为空")
    @Length(max = 15, min = 2, message = "部门名称需要在2-15字符之间")
    private String name;

    /**
     * 父级部门,判空处理
     */
    private Integer parentId = 0;


    /**
     * 部门排序等级
     */
    @NotNull(message = "展示顺序不能为空")
    private Integer seq;

    /**
     * 备注
     */
    @Length(max = 150, message = "备注只能小于150个字符")
    private String remark;
}
