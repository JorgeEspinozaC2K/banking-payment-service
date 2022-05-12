package com.banking.payment.service.app.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.banking.payment.service.app.entity.Credit;

import reactor.core.publisher.Mono;

@Service
public class PaymentWebClient {
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory;
	
	public Mono<Credit> findCredit(String id){
		return WebClient
				.create("http://localhost:8080")
				.get()
				.uri("/credit/{id}",id)
				.retrieve()
				.bodyToMono(Credit.class)
				.transformDeferred(it -> {
                    ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create("customDefaultCB");
                    return rcb.run(it, throwable -> Mono.empty());
                });
	}
	
	public Mono<Credit> saveCredit(Credit credit){
		return WebClient
				.create("http://localhost:8080")
				.post()
				.uri("/credit/save")
				.body(Mono.just(credit),Credit.class)
				.retrieve()
				.bodyToMono(Credit.class)
				.transformDeferred(it -> {
                    ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create("customDefaultCB");
                    return rcb.run(it, throwable -> Mono.empty());
                });
	}
}
