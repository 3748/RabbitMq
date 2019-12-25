package com.test.rabbitmq;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanout_sms_queue")
public class SmsConsumer {

    @RabbitHandler
    public void process(String msg) throws Exception {
        System.out.println("SMS消费者获取生产者消息msg:" + msg);
    }
}
