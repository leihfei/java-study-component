package com.lnlr.design.command;

/**
 * @author:leihfei
 * @description 命令接口
 * @date:Create in 13:41 2018/10/24
 * @email:leihfein@gmail.com
 */
public interface Command {
    void execute();

    void undo();
}
