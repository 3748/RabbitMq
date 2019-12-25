package com.test.rabbitmq.routing;

import com.test.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 路由模式----生产者
 *
 * @author gp6
 * @date 2018/9/10
 */
public class RoutingProvider {
    private final static String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明exchanges
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        // 消息内容
        String message = "I am RoutingProvider!";
        channel.basicPublish(EXCHANGE_NAME, "key2", null, message.getBytes());
        System.out.println(" RoutingProvider '" + message + "'");

        channel.close();
        connection.close();
    }
}
