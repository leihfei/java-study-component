package com.lnlr.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 11:23 2018/12/16
 * @email:leihfein@gmail.com
 */
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建一个连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("leihfei.cn");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPort(5672);
        // 创建连接
        Connection connection = connectionFactory.newConnection();
        // 创建channel
        Channel channel = connection.createChannel();
        // 使用channel发送数据
        for (int i = 0; i < 5; i++) {
            String msg = "hello RabbitMQ" + i;
            channel.basicPublish("", "test001", null, msg.getBytes());
        }
        channel.close();
        connection.close();
    }
}
