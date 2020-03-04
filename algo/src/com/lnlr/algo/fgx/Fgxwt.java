package com.lnlr.algo.fgx;

import com.lnlr.algo.utils.AlgoVisHelper;

import java.awt.*;
import java.util.Arrays;

/**
 * 分割线问题
 * 在一定时间内，每个人都随机的给别人一块钱，一定时间之后每个人的钱
 */
public class Fgxwt {
    private int[] money;
    private FgxAlgoFrame frame;
    private int sceneWidth;
    private int sceneHeight;


    public Fgxwt() {
    }

    public Fgxwt(int sceneWidth, int sceneHeight, int N) {
        int R = 50;
        this.sceneHeight = sceneHeight;
        this.sceneWidth = sceneWidth;
        money = new int[N];
        for (int i = 0; i < N; i++) {
            money[i] = 100;
        }
        frame = new FgxAlgoFrame("Welcome", sceneWidth, sceneHeight);
        EventQueue.invokeLater(() -> {
            new Thread(() -> {
                run();
            }).start();
        });
    }

    public void run() {
        while (true) {
            // 绘制数据
            Arrays.sort(money);
            frame.render(money);
            AlgoVisHelper.pause(10);
            // 更新数据
            for (int k = 0; k < 50; k++) {
                for (int i = 0; i < money.length; i++) {
                    int j = (int) (Math.random() * money.length);
                    money[j] += 1;
                    money[i] -= 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        int sceneWidth = 1000;
        int sceneHeight = 800;
        Fgxwt visualizer = new Fgxwt(sceneWidth, sceneHeight, 100);
    }
}
