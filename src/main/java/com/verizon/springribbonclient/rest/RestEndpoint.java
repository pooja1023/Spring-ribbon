package com.verizon.springribbonclient.rest;

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
public String fallbackMessageEndpoint()
{
	return "Message API is down. Please try again later";
}
}
