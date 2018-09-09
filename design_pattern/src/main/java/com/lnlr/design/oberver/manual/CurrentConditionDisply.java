package com.lnlr.design.oberver.manual;

/**
 * @author:leihfei
 * @description 一块公告板
 * @date:Create in 19:14 2018/9/9
 * @email:leihfein@gmail.com
 */
public class CurrentConditionDisply implements  Observer,DisplayElement{
    private Double temperature;

    private Double humidity;


    private Subject weatherData;

    public CurrentConditionDisply(){

    }

    public CurrentConditionDisply(Subject subject){
        weatherData = subject;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Current conditions : " + temperature + " F degress and " + humidity + " %humidity");
    }

    @Override
    public void update(Double temp, Double humidity, Double pressure) {
            this.temperature = temp;
            this.humidity = humidity;
            display();
    }
}
