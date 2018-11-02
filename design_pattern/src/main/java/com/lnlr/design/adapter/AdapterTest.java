package com.lnlr.design.adapter;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 23:10 2018/10/24
 * @email:leihfein@gmail.com
 */
public class AdapterTest {
    public static void main(String[] args) {
        Durk durk = new GreedDurk();
        Thurk thurk = new MiliThurk();

        Durk objectAdapter = new ObjectAdapter(thurk);
        durk.sign();
        durk.flay();
        thurk.sign();
        thurk.flay();
        objectAdapter.sign();
        objectAdapter.flay();

        System.out.println("---------------------------------");
        Durk classAdapter = new ClassAdapter();
        classAdapter.sign();
        classAdapter.flay();

    }
}
