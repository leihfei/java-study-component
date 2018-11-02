package com.lnlr.design.command;

import java.util.Stack;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 13:44 2018/10/24
 * @email:leihfein@gmail.com
 */
public class ControlModel implements Control {

    private Command[] offCommands;
    private Command[] onCommands;

    private Stack<Command> stack = new Stack<Command>();

    public ControlModel() {
        onCommands = new Command[5];
        offCommands = new Command[5];
        NoCommand noCommand = new NoCommand();
        for (int i = 0; i < 5; i++) {
            offCommands[i] = noCommand;
            onCommands[i] = noCommand;
        }
    }

    public void setCommand(int solt, Command onCommand, Command offCommand) {
        offCommands[solt] = offCommand;
        onCommands[solt] = onCommand;
    }

    public void onButton(int slot) {
        onCommands[slot].execute();
        stack.push(onCommands[slot]);
    }

    public void offButton(int slot) {
        offCommands[slot].execute();
        stack.push(offCommands[slot]);
    }

    /**
     * 返回上一步
     */
    public void undoButton() {
        stack.pop().undo();
    }
}
