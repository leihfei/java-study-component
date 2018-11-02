package com.lnlr.design.command;

import com.lnlr.design.command.device.Light;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 13:43 2018/10/24
 * @email:leihfein@gmail.com
 */
public class OnLightCommand implements Command {
    private Light light;

    public OnLightCommand() {
    }

    public OnLightCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.on();
    }

    public void undo() {
        light.off();
    }
}
