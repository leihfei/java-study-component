package com.lnlr.rabbitmq.confirm;

import com.lnlr.rabbitmq.config.RabbitMq;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 14:12 2018/12/16
 * @email:leihfein@gmail.com
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitMq.createChannel();
        String exchangeName = "test_confirm_cxchange";
        String routingKey = "confirm.save";
        channel.exchangeDeclare(exchangeName, "topic", true);
        String queueName = "test_confirm_queue";
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, queueingConsumer);
        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            System.out.println(new String(delivery.getBody()));
        }
    }

    /**
     * 明天领导检查：
     * 1. 云企助可视化界面在电视上显示问题。 最右边部分显示不出来。
     * 2. 模块使用分布情况，综合管理，设备管理互换。站点总数删除整行，其他的三个数字醒目点。
     * 3. 永红案列，服务器错误，老是报服务器错误。
     * 4. 明天下午需要人员支撑。
     */
}
