package com.lnlr.rabbitmq.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 12:25 2018/12/16
 * @email:leihfein@gmail.com
 */
public class RabbitMq {
    public static Channel createChannel() throws IOException, TimeoutException {
        // 创建一个连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("leihfei.cn");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPort(5672);
        // 创建连接
        Connection connection = connectionFactory.newConnection();
        // 创建channel
        com.rabbitmq.client.Channel channel = connection.createChannel();
        return channel;
    }

    public static void closeChannel(Channel channel) throws IOException, TimeoutException {
        if (channel != null) {
            channel.close();
        }
    }

    public static void closeConnect(Connection connection) throws IOException {
        if (connection != null) {
            connection.close();
        }
    }
}
