package com.lnlr.algo.mc.controller;

import com.lnlr.algo.mc.MonteCarloCircle;
import com.lnlr.algo.mc.MonteCarloPiData;

import java.awt.*;

public class MC {

    private int squareSize;
    private int N;
    private int interval = 100;

    public void setInterval(int interval) {
        if(interval <= 0){
            throw new IllegalArgumentException("大于零");
        }
        this.interval = interval;
    }

    public MC(int i, int n) {
        this.squareSize = i;
        this.N = n;
    }

    public void run() {
        MonteCarloCircle circle = new MonteCarloCircle(squareSize / 2, squareSize / 2, squareSize / 2);
        MonteCarloPiData data = new MonteCarloPiData(circle);
        for (int i = 0; i < N; i++) {
            // 绘制数据
            int x = (int) (Math.random() * squareSize);
            int y = (int) (Math.random() * squareSize);
            Point point = new Point(x, y);
            data.addPoint(point);
            if(i % interval == 0) {
                System.out.println(data.estimatePi());
            }
        }
    }

    public static void main(String[] args) {
        int squareSide = 800;
        int N = 1000000000;
        MC mc = new MC(squareSide,N);
        mc.setInterval(10000);
        mc.run();
    }
}
