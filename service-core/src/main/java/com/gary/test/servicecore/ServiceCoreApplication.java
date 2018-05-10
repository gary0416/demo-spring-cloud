package com.gary.test.servicecore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceCoreApplication.class, args);
	}
}
