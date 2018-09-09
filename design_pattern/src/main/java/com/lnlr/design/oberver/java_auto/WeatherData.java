package com.lnlr.design.oberver.java_auto;

import lombok.Setter;

import java.util.Observable;
import java.util.Observer;

/**
 * @author:leihfei
 * @description 气象站
 * @date:Create in 10:52 2018/9/8
 * @email:leihfein@gmail.com
 */
@Setter
public class WeatherData extends Observable {

    private WeatherData weatherData;

    private SendData sendData;

    public void measurementsChanged(){
        setChanged();
        notifyObservers(sendData);
    }


}
