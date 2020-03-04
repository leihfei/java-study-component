package com.lnlr.algo.listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 键盘事件
 */
public class AlgoKeyListener extends KeyAdapter {
    private boolean isAnimated = true;


    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == ' ') {
            isAnimated = !isAnimated;
        }
    }

    public boolean getAnimated() {
        return isAnimated;
    }
}
