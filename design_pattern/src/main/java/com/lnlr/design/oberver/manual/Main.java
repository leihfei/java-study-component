package com.lnlr.design.oberver.manual;

/**
 * @author:leihfei
 * @description 测试程序
 * @date:Create in 19:17 2018/9/9
 * @email:leihfein@gmail.com
 */
public class Main {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionDisply currentConditionDisply = new CurrentConditionDisply(weatherData);
        weatherData.setHumidity(12.3);
        weatherData.setTemperature(3123D);
        weatherData.setPressure(80D);
        weatherData.measurementChanged();
    }
}
