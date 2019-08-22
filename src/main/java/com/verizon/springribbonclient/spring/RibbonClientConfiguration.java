package com.verizon.springribbonclient.spring;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RibbonClientConfiguration {
	@Bean
	public MessageConverter  msgcvt(){
		return new Jackson2JsonMessageConverter();
	}
	@Bean
	public SimpleRabbitListenerContainerFactory rabbitconfact(ConnectionFactory conn, 
			SimpleRabbitListenerContainerFactoryConfigurer configurer) {
		SimpleRabbitListenerContainerFactory factory=new SimpleRabbitListenerContainerFactory();
		factory.setMessageConverter(msgcvt());
		configurer.configure(factory, conn);
		return factory;
	}
}
