package com.lnlr.algo.mc;

import com.lnlr.algo.utils.AlgoVisHelper;

import java.awt.*;

/**
 * 模特卡洛随机模拟
 */
public class MonteCarloVisulizer {
    private MonteCarloAlgoFrame frame;
    private int sceneWidth;
    private int sceneHeight;

    private MonteCarloPiData data;

    private Integer N;

    private int inside = 0;


    public MonteCarloVisulizer() {
    }

    public MonteCarloVisulizer(int sceneWidth, int sceneHeight, MonteCarloPiData data, int N) {
        if (sceneHeight != sceneWidth) {
            throw new RuntimeException("长宽不一致");
        }
        this.N = N;
        this.data = data;
        this.sceneHeight = sceneHeight;
        this.sceneWidth = sceneWidth;
        EventQueue.invokeLater(() -> {
            frame = new MonteCarloAlgoFrame("Welcome", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    public void run() {
        while (true) {
            for (int i = 0; i < N; i++) {
                if (i % 50 == 0) {
                    frame.render(data);
                    AlgoVisHelper.pause(10);
                    System.out.println(data.estimatePi());
                }
                // 绘制数据
                int x = (int) (Math.random() * frame.getCanvasWidth());
                int y = (int) (Math.random() * frame.getCanvasHeight());
                Point point = new Point(x, y);
                data.addPoint(point);
            }
        }
    }

    public static void main(String[] args) {
        int sceneWidth = 800;
        int sceneHeight = 800;
        MonteCarloPiData piData = new MonteCarloPiData(new MonteCarloCircle(400, 400, 400));
        MonteCarloVisulizer visualizer = new MonteCarloVisulizer(sceneWidth, sceneHeight, piData, 50000);
    }
}
