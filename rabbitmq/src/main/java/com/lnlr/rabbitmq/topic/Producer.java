package com.lnlr.rabbitmq.topic;

import com.lnlr.rabbitmq.config.RabbitMq;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 12:25 2018/12/16
 * @email:leihfein@gmail.com
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMq.createChannel();
        String exchangeName = "test_topic_exchange";
        String routeKey1 = "user.save";
        String routeKey2 = "user.update";
        String routeKey3 = "user.delete.abc";
        // 发送消息
        String msg = "hello world rabbitmq 4 top exchange message ....";
        channel.basicPublish(exchangeName, routeKey1, null, msg.getBytes());
        channel.basicPublish(exchangeName, routeKey2, null, msg.getBytes());
        channel.basicPublish(exchangeName, routeKey3, null, msg.getBytes());
        RabbitMq.createChannel();
    }
}
