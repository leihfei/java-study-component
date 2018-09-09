package com.lnlr.design.oberver.manual;

/**
 * @author:leihfei
 * @description 观察者接口
 * @date:Create in 19:07 2018/9/9
 * @email:leihfein@gmail.com
 */
public interface Observer {
    void update(Double temp,Double humidity,Double pressure);
}
