package com.lnlr.algo.fgx;

import com.lnlr.algo.utils.AlgoVisHelper;

import javax.swing.*;
import java.awt.*;

public class FgxAlgoFrame extends JFrame {

    private Integer canvasWidth;
    private Integer canvasHeight;
    private int[] money;

    public FgxAlgoFrame() {
    }

    public FgxAlgoFrame(String title) {
        this(title, 1024, 768);
    }

    public FgxAlgoFrame(String title, Integer canvasWidth, Integer canvasHeight) {
        super(title);
        setSize(new Dimension(canvasWidth, canvasHeight));
        setResizable(true);
        JPanel jPanel = new AlgoPanel();
        setContentPane(jPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.canvasHeight = canvasHeight;
        this.canvasWidth = canvasWidth;
    }


    public void render(int[] money) {
        this.money = money;
        repaint();
    }

    public Integer getCanvasWidth() {
        return canvasWidth;
    }

    public Integer getCanvasHeight() {
        return canvasHeight;
    }

    private class AlgoPanel extends JPanel {
        public AlgoPanel() {
            super(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            // 抗锯齿
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.addRenderingHints(hints);

            // 具体绘制
            AlgoVisHelper.setStrokeWidth(g2d, 1);
            if (money != null) {
                // 每一个人的宽度
                int w = canvasWidth / money.length;
                for (int i = 0; i < money.length; i++) {
                    if (money[i] > 0) {
                        AlgoVisHelper.setColor(g2d, Color.BLUE);
                        AlgoVisHelper.fillRectangle(g2d, i * w + 1, canvasHeight / 2 - money[i], w - 1, money[i]);
                    } else {
                        AlgoVisHelper.setColor(g2d, Color.RED);
                        AlgoVisHelper.fillRectangle(g2d, i * w + 1, canvasHeight / 2, w - 1, -money[i]);
                    }
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }

}
