package com.lnlr.design.oberver.java_auto;

import com.lnlr.design.oberver.manual.DisplayElement;
import lombok.NoArgsConstructor;

import java.util.Observable;
import java.util.Observer;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 20:00 2018/9/9
 * @email:leihfein@gmail.com
 */
@NoArgsConstructor
public class CurrentConditionDisplay implements Observer, DisplayElement {
    private SendData sendData;
    private Observable weatherData;


    public CurrentConditionDisplay(Observable observable){
        this.weatherData = observable;
        weatherData.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        sendData = (SendData) arg;
        display();
    }

    @Override
    public void display() {
        System.out.println("输出天气数据: 湿度" + sendData.getHumidity() + "  温度：" + sendData.getTemperature() + " 压力：" + sendData.getPressure());
    }
}
