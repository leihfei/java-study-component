package com.lnlr.design.oberver.java_auto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author:leihfei
 * @description 气象站发送的数据
 * @date:Create in 10:51 2018/9/8
 * @email:leihfein@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendData {
    /**
     * 温度
     */
    private Double temperature;

    /**
     * 湿度
     */
    private Double humidity;

    /**
     * 压力
     */
    private Double pressure;

}
