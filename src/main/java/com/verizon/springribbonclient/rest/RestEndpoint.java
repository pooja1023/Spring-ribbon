package com.verizon.springribbonclient.rest;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/ribbon-client")
public class RestEndpoint {
	@Autowired
	private LoadBalancerClient loadbalclient;
	@Autowired
	private RabbitTemplate rabbittemp;
@RequestMapping
@HystrixCommand(fallbackMethod = "fallbackMessageEndpoint")
public String consumeMessage() {
	RestTemplate resttemplate=new RestTemplate();
	ServiceInstance serviceinst=loadbalclient.choose("Config-server-client");
	String host=serviceinst.getHost();
	int port=serviceinst.getPort();
	String response=resttemplate.getForObject("http://"+host+":"+port+"/message", String.class);
	return response;
}
@RequestMapping("/readMsg")
public String readMessage() {
	Message message = rabbittemp.receive("directqueue");
	return new String(message.getBody());
}
@RequestMapping("/readMsg2")
public String readMessage2() {
	Message message = rabbittemp.receive("directqueue2");
	return new String(message.getBody());
}
public String fallbackMessageEndpoint()
{
	return "Message API is down. Please try again later";
}
}
