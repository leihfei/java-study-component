package com.lnlr.design.command.device;

/**
 * @author:leihfei
 * @description 灯
 * @date:Create in 10:42 2018/10/24
 * @email:leihfein@gmail.com
 */
public class Light {

    String loc = "";

    public Light(String loc){
        this.loc = loc;
    }

    public void on(){
        System.out.println(loc + " on");
    }

    public void off(){
        System.out.println(loc + " off");
    }
}
