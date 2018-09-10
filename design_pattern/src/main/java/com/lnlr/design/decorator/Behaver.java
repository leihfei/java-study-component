package com.lnlr.design.decorator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author:leihfei
 * @description 基础类，抽象类
 * @date:Create in 21:08 2018/9/10
 * @email:leihfein@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public  abstract class Behaver {
    public String description;

    public abstract Double cost();

}
