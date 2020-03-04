package com.lnlr.memory;

import java.nio.ByteBuffer;

/**
 * @author leihf
 * @email leihfein@gmail.com
 * @description 直接内存溢出
 * -verbose:gc -Xms10M -Xmx10M -XX:MaxDirectMemorySize=10M -Xss128k -XX:+PrintGCDetails
 * @date 2019-07-14 08:11:20
 */
public class DirectOutOfMemory {
    private static final int ONE_GB = 1024 * 1024 * 1024;
    private static int count = 1;

    public static void main(String[] args) {
        try {
            while (true) {
                ByteBuffer buffer = ByteBuffer.allocateDirect(ONE_GB);
                count++;
            }
        } catch (Exception e) {
            System.out.println("Exception:instance created " + count);
            e.printStackTrace();
        } catch (Error e) {
            System.out.println("Error:instance created " + count);
            e.printStackTrace();
        }
    }
}
