package com.lnlr.algo.mc;

import java.awt.*;
import java.util.LinkedList;

public class MonteCarloPiData {

    private MonteCarloCircle circle;

    private LinkedList<Point> points;

    private int insideCircle = 0;

    public MonteCarloCircle getCircle() {
        return circle;
    }

    public MonteCarloPiData(MonteCarloCircle circle) {
        this.circle = circle;
        this.points = new LinkedList<>();
    }

    public Point getPint(int i) {
        if (i < 0 || i >= points.size()) {
            // 发生错误;
            return null;
        }
        return points.get(i);
    }

    public void addPoint(Point point) {
        points.add(point);
        if (circle.contains(point)) {
            insideCircle++;
        }
    }

    public double estimatePi() {
        if (points.size() == 0) {
            return 0;
        }
        int circleArea = insideCircle;
        int squareArea = points.size();
        double pi = 4 * (double) circleArea / squareArea;
        return pi;
    }

    public int getPointsSize(){
        return  points.size();
    }
}
