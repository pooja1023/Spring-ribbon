package com.verizon.springribbonclient.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
@Component
public class FanoutListener {
	@RabbitListener(queues= {"fanoutqueue1","fanoutqueue2"})
	public void readQueue(String message)
	{
		System.out.println("Message read from two fanout queues"+message);
	}
}
