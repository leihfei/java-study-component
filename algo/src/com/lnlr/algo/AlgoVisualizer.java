package com.lnlr.algo;

import com.lnlr.algo.entity.Circle;
import com.lnlr.algo.listener.AlgoKeyListener;
import com.lnlr.algo.utils.AlgoVisHelper;

import java.awt.*;

/**
 * 算法控制器
 */
public class AlgoVisualizer {
    private Circle[] circles;
    private AlgoFrame frame;
    private int sceneWidth;
    private int sceneHeight;


    private AlgoKeyListener keyListener;

    public AlgoVisualizer() {
    }

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N, AlgoKeyListener keyListener) {
        int R = 50;
        this.sceneHeight = sceneHeight;
        this.sceneWidth = sceneWidth;
        this.keyListener = keyListener;
        circles = new Circle[N];
        for (int i = 0; i < N; i++) {
            int x = (int) (Math.random() * (sceneWidth - 2 * R) + R);
            int y = (int) (Math.random() * (sceneHeight - 2 * R) + R);
            int vx = (int) (Math.random() * 11) - 5;
            int vy = (int) (Math.random() * 11) - 5;
            Circle circle = new Circle(x, y, R, vx, vy);
            circles[i] = circle;
        }
        frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
        frame.addKeyListener(keyListener);
        EventQueue.invokeLater(() -> {
            new Thread(() -> {
                run();
            }).start();
        });
    }

    public void run() {
        while (true) {
            // 绘制数据
            frame.render(circles);
            AlgoVisHelper.pause(20);
            if (keyListener.getAnimated()) {
                // 更新数据
                for (Circle circle : circles) {
                    circle.move(0, 0, sceneWidth, sceneHeight);
                }
            }
        }
    }

    public static void main(String[] args) {
        int sceneWidth = 800;
        int sceneHeight = 800;
        AlgoKeyListener key = new AlgoKeyListener();
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, 20, key);
    }
}
