package com.lnlr.design.command;

/**
 * @author:leihfei
 * @description 控制器
 * @date:Create in 10:46 2018/10/24
 * @email:leihfein@gmail.com
 */
public interface Control {

    void onButton(int slot);

    void offButton(int slot);

    void undoButton();
}
