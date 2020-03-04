package com.lnlr.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leihf
 * @email leihfein@gmail.com
 * @description 常量区内存溢出
 * -verbose:gc -Xms10M -Xmx10M -Xss128k -XX:+PrintGCDetails
 * @date 2019-07-14 08:10:58
 */
public class ContenceOutOfMemory {
    public static void main(String[] args) {
        try {
            List<String> stringList = new ArrayList<String>();
            int item = 0;
            while (true) {
                stringList.add(String.valueOf(item++).intern());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
