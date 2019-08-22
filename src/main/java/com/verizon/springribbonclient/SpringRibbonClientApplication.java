package com.verizon.springribbonclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Import;

import com.netflix.client.config.IClientConfig;
import com.verizon.springribbonclient.config.RibbonConfiguration;
import com.verizon.springribbonclient.queue.ActiveQueueListener;
import com.verizon.springribbonclient.queue.FanoutListener;
import com.verizon.springribbonclient.queue.TopicListener;
import com.verizon.springribbonclient.rest.RestEndpoint;
import com.verizon.springribbonclient.spring.RibbonClientConfiguration;

@SpringBootApplication(scanBasePackageClasses = {IClientConfig.class,RestEndpoint.class,ActiveQueueListener.class,TopicListener.class,FanoutListener.class})
@RibbonClient(name="ping-a-server",configuration=RibbonConfiguration.class)
@EnableEurekaClient
@EnableCircuitBreaker
@Import(value=RibbonClientConfiguration.class)
public class SpringRibbonClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRibbonClientApplication.class, args);
	}

}
