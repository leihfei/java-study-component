package com.lnlr.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leihf
 * @email leihfein@gmail.com
 * @description 堆内存溢出
 * -verbose:gc -Xms10M -Xmx10M -Xss128k -XX:+PrintGCDetails
 * @date 2019-07-14 08:00:07
 */
public class HeadOutOfMemroyException {


    public static void main(String[] args) {
        System.out.println("演示堆内存溢出.....");
        Integer count = 0;
        List<Person> lists = new ArrayList<>();
        while (true) {
            lists.add(new Person());
            count++;
            System.out.println("instance=" + count);
        }
    }
}

class Person {

}