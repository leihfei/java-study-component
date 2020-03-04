package com.lnlr.rabbitmq.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * @author:leihfei
 * @description:
 * @date:Create in 14:12 2018/12/16
 * @email:leihfein@gmail.com
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setPort(5672);
        connectionFactory.setHost("leihfei.cn");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.confirmSelect();
        String exchangeName = "test_confirm_cxchange";
        String routingKey = "confirm.save";
        channel.basicPublish(exchangeName, routingKey, null, "hello confrim message".getBytes());
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("失败进入该方法");
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("成功进入该方法");

            }
        });

    }
}
