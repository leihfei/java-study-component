package com.lnlr.design.decorator.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author:leihfei
 * @description
 * @date:Create in 21:21 2018/9/10
 * @email:leihfein@gmail.com
 */
public class TestIO {
    public static void main(String[] args) throws IOException {
        InputStream in = new FileInputStream(new File("e://c.txt"));
        in = new CaseUpIO(in);
        int c = -1;
        while((c = in.read()) > 0){
            System.out.print((char)c);
        }
    }
}
