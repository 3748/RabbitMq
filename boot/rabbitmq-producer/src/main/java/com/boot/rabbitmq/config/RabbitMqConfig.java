package com.boot.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author gp6
 * @date 2019/12/25
 */
@Component
public class RabbitMqConfig {

    @Bean
    public Queue fanOutEmailQueue() {
        // 邮件队列
        String emailQueue = "fanout_email_queue";
        return new Queue(emailQueue);
    }

    @Bean
    public Queue fanOutSmsQueue() {
        // 短信队列
        String smsQueue = "fanout_sms_queue";
        return new Queue(smsQueue);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        // 定义交换机
        String exchangeName = "fanoutExchange";
        return new FanoutExchange(exchangeName);
    }

    /**
     * 队列与交换机绑定邮件队列
     *
     * @param fanOutEmailQueue 邮件队列
     * @param fanoutExchange   交换机
     * @return Binding
     */
    @Bean
    Binding bindingExchangeEmail(Queue fanOutEmailQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanOutEmailQueue).to(fanoutExchange);
    }

    /**
     * 队列与交换机绑定短信队列
     *
     * @param fanOutSmsQueue 短信队列
     * @param fanoutExchange 交换机
     * @return Binding
     */
    @Bean
    Binding bindingExchangeSms(Queue fanOutSmsQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanOutSmsQueue).to(fanoutExchange);
    }
}
