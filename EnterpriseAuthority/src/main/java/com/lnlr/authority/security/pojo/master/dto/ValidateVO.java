package com.lnlr.authority.security.pojo.master.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author:leihfei
 * @description 测试validator
 * @date:Create in 0:35 2018/9/3
 * @email:leihfein@gmail.com
 */
@Data
public class ValidateVO {

    @NotBlank(message = "参数不能为空/空字符串")
    public String msg;

    @NotNull(message = "参数不能为空")
    public String id;
}
