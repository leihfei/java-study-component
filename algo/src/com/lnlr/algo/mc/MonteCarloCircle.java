package com.lnlr.algo.mc;

import java.awt.*;

public class MonteCarloCircle {
    public int x, y;
    private int r;

    public MonteCarloCircle(int x, int y, int r) {
        this.x = x;
        this.r = r;
        this.y = y;
    }

    public int getR() {
        return r;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean contains(Point point) {
        return Math.pow(point.getX() - x, 2) + Math.pow(point.getY() - y, 2) <= r * r;
    }
}
