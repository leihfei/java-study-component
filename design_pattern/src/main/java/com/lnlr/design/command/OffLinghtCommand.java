package com.lnlr.design.command;

import com.lnlr.design.command.device.Light;

/**
 * @author:leihfei
 * @description 关灯的命令
 * @date:Create in 13:40 2018/10/24
 * @email:leihfein@gmail.com
 */
public class OffLinghtCommand implements Command {
    private Light light;

    public OffLinghtCommand() {
    }

    public OffLinghtCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.off();
    }

    public void undo() {
        light.on();
    }
}
