package com.lnlr.security.pojo.master.vo.dept;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author:leihfei
 * @description 只有部门名称和id的视图
 * @date:Create in 23:49 2018/10/25
 * @email:leihfein@gmail.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "短部门数据")
public class DeptShortVO {
    private Integer id;
    private String name;
}
