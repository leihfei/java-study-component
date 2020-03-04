package com.lnlr.algo.maze.demo1;

public class Main {
    public static void main(String[] args) {
        String fileName = "D:\\IDEA_WORK\\xzl_work\\java-study-component\\algo\\src\\com\\lnlr\\algo\\maze\\demo1\\maze_101_101.txt";
        MazeData mazeData = new MazeData(fileName);
        mazeData.print();
    }
}
