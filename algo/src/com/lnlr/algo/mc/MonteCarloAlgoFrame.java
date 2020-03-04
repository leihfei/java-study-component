package com.lnlr.algo.mc;

import com.lnlr.algo.utils.AlgoVisHelper;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class MonteCarloAlgoFrame extends JFrame {

    private Integer canvasWidth;
    private Integer canvasHeight;
    private MonteCarloPiData data;

    public MonteCarloAlgoFrame() {
    }

    public MonteCarloAlgoFrame(String title) {
        this(title, 1024, 768);
    }

    public MonteCarloAlgoFrame(String title, Integer canvasWidth, Integer canvasHeight) {
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


    public void render(MonteCarloPiData data) {
       this.data = data;
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
            AlgoVisHelper.setColor(g2d, Color.BLUE);
            AlgoVisHelper.strokeCircle(g2d, data.getCircle().getX(), data.getCircle().getY(), data.getCircle().getR());
            // 绘制点
            for (int i = 0; i < data.getPointsSize(); i++) {
                Point point = data.getPint(i);
                if (data.getCircle().contains(point)) {
                    //园内
                    AlgoVisHelper.setColor(g2d, Color.RED);
                } else {
                    // 圆外
                    AlgoVisHelper.setColor(g2d, Color.GREEN);
                }
                AlgoVisHelper.fillCircle(g2d, point.x, point.y, 4);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(canvasWidth, canvasHeight);
    }
}
