package com.test.rabbitmq.ps;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import com.test.rabbitmq.util.ConnectionUtil;

/**
* 订阅模式----生产者
*
* @author gp6
* @date 2018/9/10
*/
public class PsProvider {

	private final static String EXCHANGE_NAME = "ps_exchange_name";

	public static void main(String[] argv) throws Exception {
		// 获取到连接以及mq通道
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();

		// 声明exchange(交换机)
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

		// 消息内容
		String message = "I am ps_exchange_name!";

		// 消息持久化
		AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
		// 1:不持久化 2:持久化
		builder.deliveryMode(2);
		AMQP.BasicProperties properties = builder.build();
		/*
		 * EXCHANGE_NAME: 交换机名称
		 * "" : 路由key
		 * properties: 基本属性
		 * message: 消息体
		 */
		//channel.basicPublish(EXCHANGE_NAME, "", properties, message.getBytes());
		channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
		System.out.println(" PsProvider '" + message + "'");




		channel.close();
		connection.close();
	}
}