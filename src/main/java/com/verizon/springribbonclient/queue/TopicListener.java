package com.verizon.springribbonclient.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.verizon.springribbonclient.model.Employee;

@Component
public class TopicListener {
@RabbitListener(queues="topicqueue")
public void readQueue(Employee emp)
{
	System.out.println("Message read from topic queue"+emp.getName());
}
}
