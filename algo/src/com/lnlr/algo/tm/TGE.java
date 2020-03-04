package com.lnlr.algo.tm;

public class TGE {
    private int N;

    private int wins = 0;

    public TGE(int n) {
        this.N = n;
    }

    public void run(boolean changeDoor) {
        for (int i = 0; i < N; i++) {
            boolean play = play(changeDoor);
            if (play) {
                wins++;
            }
        }
        System.out.println(changeDoor ? "Change" : "Not Change");
        System.out.println("winning rate is " + (double) wins / N);
    }


    public boolean play(boolean changeDoor) {
        int prizeDoor = (int) (Math.random() * 3);
        int playChoice = (int) (Math.random() * 3);
        if (prizeDoor == playChoice) {
            return changeDoor ? false : true;
        } else {
            return changeDoor ? true : false;
        }
    }

    public static void main(String[] args) {
        TGE tge = new TGE(1000000);
        tge.run(true);
        System.out.println("-------------------");
        tge.run(false);
    }
}
