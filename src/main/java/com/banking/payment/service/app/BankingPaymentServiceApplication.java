package com.banking.payment.service.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BankingPaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingPaymentServiceApplication.class, args);
	}

}
