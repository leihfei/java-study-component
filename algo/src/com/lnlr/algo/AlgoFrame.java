package com.lnlr.algo;

import com.lnlr.algo.entity.Circle;
import com.lnlr.algo.utils.AlgoVisHelper;

import javax.swing.*;
import java.awt.*;

public class AlgoFrame extends JFrame {

    private Integer canvasWidth;
    private Integer canvasHeight;
    private Circle[] circles;

    public AlgoFrame() {
    }

    public AlgoFrame(String title) {
        this(title, 1024, 768);
    }

    public AlgoFrame(String title, Integer canvasWidth, Integer canvasHeight) {
        super(title);
        setSize(new Dimension(canvasWidth, canvasHeight));
        setLocationRelativeTo(null);
        setResizable(true);
        JPanel jPanel = new AlgoPanel();
        setContentPane(jPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.canvasHeight = canvasHeight;
        this.canvasWidth = canvasWidth;
    }


    public void render(Circle[] circles) {
        this.circles = circles;
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
            AlgoVisHelper.setColor(g2d, Color.RED);
            if (circles != null) {
                for (Circle circle : circles) {
                    AlgoVisHelper.strokeCircle(g2d, circle.x, circle.y, circle.getR());
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }

}
