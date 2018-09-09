package com.lnlr.design.oberver.manual;

/**
 * @author:leihfei
 * @description 主题接口
 * @date:Create in 19:06 2018/9/9
 * @email:leihfein@gmail.com
 */
public interface Subject {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObserver();

}
