package com.lnlr.algo.maze.demo1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class MazeData {

    private int N, M;

    private char[][] maze;

    public MazeData(String filename) {
        if (filename == null) {
            throw new IllegalArgumentException("file is don't found");
        }
        Scanner scanner = null;
        try {
            File file = new File(filename);
            if (!file.exists()) {
                throw new IllegalArgumentException("file exists");
            }
            InputStream inputStream = new FileInputStream(file);
            scanner = new Scanner(inputStream);
            String nmline = scanner.nextLine();
            String[] nm = nmline.trim().split("\\s+");
            N = Integer.parseInt(nm[0]);
            M = Integer.parseInt(nm[1]);
            maze = new char[M][N];
            for (int i = 0; i < N; i++) {
                String line = scanner.nextLine();
                if (line.length() != M) {
                    throw new IllegalArgumentException("文件数据异常!");
                }
                for (int j = 0; j < M; j++) {
                    maze[i][j] = line.charAt(j);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    public void print(){
        for (int i = 0; i <N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    public int N() {
        return N;
    }

    public int M() {
        return M;
    }

    public char getMaze(int i, int j) {
        if (!isArea(i, j)) {
            throw new IllegalArgumentException("");
        }
        return maze[i][j];
    }

    private boolean isArea(int i, int j) {
        return i >= 0 && i <= M && j >= 0 && j <= N;
    }
}
