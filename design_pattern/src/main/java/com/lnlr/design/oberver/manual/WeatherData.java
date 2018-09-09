package com.lnlr.design.oberver.manual;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:leihfei
 * @description 气象站类
 * @date:Create in 19:09 2018/9/9
 * @email:leihfein@gmail.com
 */

@Setter
public class WeatherData implements Subject {
    private List<Observer> observers;

    // 天气数据
    private Double temperature;

    private Double humidity;

    private Double pressure;

    public WeatherData() {
        this.observers = new ArrayList<Observer>();
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        if (observers.indexOf(observer) >= 0) {
            observers.remove(observer);
        }
    }

    /**
     * 发送公告
     */
    public void notifyObserver() {
        System.out.println("向订阅者推送数据...");
        observers.forEach(observer -> {
            System.out.println("发送数据....");
            observer.update(temperature, humidity, pressure);
        });
    }

    /**
     * 当数据改变时，调用该接口
     */
    public void measurementChanged() {
        System.out.println("气象站调用发布天气接口");
        notifyObserver();
    }
}
