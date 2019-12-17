package com.test.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * MQ连接获取
 *
 * @author gp6
 * @date 2018/9/10
 */
public class ConnectionUtil {

    public static Connection getConnection() throws Exception {
        // 定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        // 设置服务地址
        factory.setHost("localhost");

        // 端口
        factory.setPort(5672);

        // 设置账号信息，用户名、密码、vhosts
        factory.setVirtualHost("/root");
        factory.setUsername("root");
        factory.setPassword("123456");

        // 通过工程获取连接
        return factory.newConnection();
    }

}
