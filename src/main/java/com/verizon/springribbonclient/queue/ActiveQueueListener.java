package com.verizon.springribbonclient.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ActiveQueueListener {
@RabbitListener(queues="directqueue")
public void readQueue(String message)
{
	System.out.println("Message read from queue"+message);
}
}
