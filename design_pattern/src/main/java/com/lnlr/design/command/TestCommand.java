package com.lnlr.design.command;

import com.lnlr.design.command.device.Light;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 13:56 2018/10/24
 * @email:leihfein@gmail.com
 */
public class TestCommand {
    public static void main(String[] args) {
        Light light = new Light("台灯");
        OffLinghtCommand offLinghtCommand = new OffLinghtCommand(light);
        OnLightCommand onLightCommand = new OnLightCommand(light);

        ControlModel controlModel = new ControlModel();
        controlModel.setCommand(1, onLightCommand, offLinghtCommand);


        controlModel.onButton(1);
        controlModel.offButton(1);
        controlModel.undoButton();
        controlModel.undoButton();
    }
}
