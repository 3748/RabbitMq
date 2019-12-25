package com.boot.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
* 生产者
*
* @author gp6
* @date 2019/12/25
*/
@Component
public class Producer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    void send(String queueName) {
        String msg = "my_fanout_msg:" + new Date();
        System.out.println(msg + ":" + msg);
        amqpTemplate.convertAndSend(queueName, msg);
    }
}
