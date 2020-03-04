package com.lnlr.rabbitmq.topic;

import com.lnlr.rabbitmq.config.RabbitMq;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 12:25 2018/12/16
 * @email:leihfein@gmail.com
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 得到channel
        Channel channel = RabbitMq.createChannel();
        // 得到交换机
        String exchangeName = "test_topic_exchange";
        String exchangeType = "topic";
        String queueName = "test_topic_queue";
        String routingKey = "user.*";
        // 申明交换机
        channel.exchangeDeclare(exchangeName, exchangeType, true, false, null);
        // 申明队列
        channel.queueDeclare(queueName, false, false, false, null);
        // 建立交换机与队列之间的绑定
        channel.queueBind(queueName, exchangeName, routingKey);
        //
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.err.println("消费端:" + message);
        }
    }
}
