package com.boot.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gp6
 * @date 2019/12/25
 */
@RestController
public class ProducerController {

    @Autowired
    private Producer producer;

    @RequestMapping("/sendFanout")
    public String sendFanout(String queueName) {
        producer.send(queueName);
        return "success";
    }
}
