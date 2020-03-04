package com.lnlr.memory;

/**
 * @author leihf
 * @email leihfein@gmail.com
 * @description 栈溢出
 * @date 2019-07-14 08:07:08
 */
public class StackOutOfMemroy {

    private Integer count = 0;

    private void counter(){
        count++;
        counter();
    }

    public static void main(String[] args) {
        StackOutOfMemroy sc = new StackOutOfMemroy();
        sc.counter();
    }
}
