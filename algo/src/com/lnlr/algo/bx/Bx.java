package com.lnlr.algo.bx;

public class Bx {

    private float chooise = 0.2f;

    private int playTime = 5;

    private int N;

    public Bx(int N, float chooise, int playTime) {
        this.chooise = chooise;
        this.N = N;
        this.playTime = playTime;
    }

    public void run() {
        int wins = 0;
        for (int i = 0; i < N; i++) {
            if (play()) {
                wins++;
            }
        }
        System.out.println("中奖率:" + (double) wins / N);
    }

    public boolean play() {
        for (int i = 0; i < playTime; i++) {
            if (Math.random() < chooise) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        float chooise = 0.2f;
        int N = 1000000000;
        int playTime = 5;
        Bx bx = new Bx(N,chooise,playTime);
        bx.run();
    }

}
