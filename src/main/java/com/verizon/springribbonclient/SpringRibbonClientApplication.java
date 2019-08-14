package com.verizon.springribbonclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.netflix.client.config.IClientConfig;
import com.verizon.springribbonclient.config.RibbonConfiguration;
import com.verizon.springribbonclient.rest.RestEndpoint;

@SpringBootApplication(scanBasePackageClasses = {com.netflix.client.config.IClientConfig.class,RestEndpoint.class})
@RibbonClient(name="ping-a-server",configuration=RibbonConfiguration.class)
@EnableEurekaClient
@EnableCircuitBreaker
public class SpringRibbonClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRibbonClientApplication.class, args);
	}

}
