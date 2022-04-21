package com.banking.payment.service.app.webclient;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import com.banking.payment.service.app.entity.Credit;

import reactor.core.publisher.Mono;

public class PaymentWebClient {
	
	private Builder paymentWebClient = WebClient.builder();
	
	public Mono<Credit> findCredit(String id){
		return paymentWebClient.build()
				.get()
				.uri("http://localhost:8082/{id}",id)
				.retrieve()
				.bodyToMono(Credit.class);
	}
}
