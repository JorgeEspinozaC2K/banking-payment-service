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
				.uri("http://localhost:8084/api/v1/credit/{id}",id)
				.retrieve()
				.bodyToMono(Credit.class);
	}
	
	public Mono<Credit> saveCredit(Credit credit){
		return paymentWebClient.build()
				.post()
				.uri("http://localhost:8084/api/v1/credit/save",credit)
				.retrieve()
				.bodyToMono(Credit.class);
	}
}
