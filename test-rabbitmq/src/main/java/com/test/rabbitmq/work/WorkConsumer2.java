package com.test.rabbitmq.work;

import com.test.rabbitmq.util.ConnectionUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

/**
 * Work模式----消费者2
 *
 * @author gp6
 * @date 2018/9/10
 */
public class WorkConsumer2 {

    private final static String QUEUE_NAME = "work_queue";

    public static void main(String[] argv) throws Exception {

        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 同一时刻服务器只会发一条消息给消费者,如果该行注释,两个消费者获取的消息数量会一样,一个获取偶数的消息,一个获取奇数的消息
        channel.basicQos(1);

        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列，手动返回完成状态
        channel.basicConsume(QUEUE_NAME, false, consumer);

        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" WorkConsumer2 '" + message + "'");
            // 休眠1秒
            Thread.sleep(1000);

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}