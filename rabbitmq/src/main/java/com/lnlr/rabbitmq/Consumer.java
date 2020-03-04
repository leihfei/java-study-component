package com.lnlr.rabbitmq;

import com.rabbitmq.client.*;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 11:22 2018/12/16
 * @email:leihfein@gmail.com
 */
public class Consumer {
    public static void main(String[] args) throws Exception {
        // 创建一个连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("leihfei.cn");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPort(5672);
        // 创建连接
        Connection connection = connectionFactory.newConnection();
        // 创建channel
        Channel channel = connection.createChannel();
        String queueName = "test001";
        // 申明一个消息队列
        channel.queueDeclare(queueName, true, false, false, null);
        // 创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        // 设置channel
        channel.basicConsume(queueName, true, queueingConsumer);
        while (true) {
            // 获取消息
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            AMQP.BasicProperties properties = delivery.getProperties();
            String message = new String(delivery.getBody());
            System.err.println("消费端:" + message);
            Envelope envelope = delivery.getEnvelope();
            String exchange = envelope.getExchange();
            System.out.println("交换机:" + exchange);
            System.out.println("routingKey:" + envelope.getRoutingKey());
        }
    }
}
