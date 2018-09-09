package com.lnlr.design.oberver.java_auto;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 20:02 2018/9/9
 * @email:leihfein@gmail.com
 */
public class Main {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(weatherData);
        weatherData.setSendData(new SendData(21D,321.2D,3.3D));
        weatherData.measurementsChanged();
    }
}
