package com.test.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import com.test.rabbitmq.util.ConnectionUtil;

/**
 * 简单队列----生产者
 *
 * @author gp6
 * @date 2018/9/10
 */
public class SimpleProducer {

    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();

        // 从连接中创建通道,相当于JDBC中的statement
        Channel channel = connection.createChannel();

        // 声明（创建）队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 不开启事务
//        String message = "I am simple_queue!";
//        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
//        System.out.println(" SimpleProducer '" + message + "'");

        // 开启事务
        try {
            String message = "I am simple_queue!";
            // 开启事务
            channel.txSelect();
            // 往队列中发出一条消息，使用rabbitmq默认交换机
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            // 提交事务
            channel.txCommit();
        } catch (Exception e) {
            e.printStackTrace();
            // 事务回滚
            channel.txRollback();
        }


        // 关闭通道和连接
        channel.close();
        connection.close();
    }
}