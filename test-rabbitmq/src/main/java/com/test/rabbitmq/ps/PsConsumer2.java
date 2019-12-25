package com.test.rabbitmq.ps;


/**
 * 订阅模式----消费者2
 *
 * @author gp6
 * @date 2018/9/10
 */
public class PsConsumer2 {

    private final static String QUEUE_NAME = "ps_queue2";

    public static void main(String[] argv) throws Exception {
        new PsCommonMethod(QUEUE_NAME);
    }
}