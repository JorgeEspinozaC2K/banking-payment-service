package com.banking.payment.service.app.service;

import com.banking.payment.service.app.model.Payment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaymentService{
	
	
	public Flux<Payment> findAllPyments();
	
	public Flux<Payment> findPaymentByCreditId(String creditId);
	
	public Mono<Payment> findPaymentById(String id);
	
	public Mono<Payment> savePayment(Payment Payment);
	
	public Mono<Void> deletePayment(Payment payment);

}
