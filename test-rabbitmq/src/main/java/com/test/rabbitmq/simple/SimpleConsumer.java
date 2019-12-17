package com.test.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import com.test.rabbitmq.util.ConnectionUtil;

/**
 * 简单队列----消费者
 *
 * @author gp6
 * @date 2018/9/10
 */
public class SimpleConsumer {

    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] argv) throws Exception {

        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明队列,如果消费者可以确定队列存在,则此行可以忽略
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);

        // 获取消息后,消息即删除
        while (true) {
            // 此处会阻塞,等待消息
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" SimpleConsumer '" + message + "'");
        }
    }
}