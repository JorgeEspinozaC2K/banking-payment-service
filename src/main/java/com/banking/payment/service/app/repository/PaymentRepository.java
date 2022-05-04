package com.banking.payment.service.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.banking.payment.service.app.model.Payment;

import reactor.core.publisher.Flux;

public interface PaymentRepository extends ReactiveMongoRepository<Payment, String> {
	
	/**
	 * 
	 * @param creditId
	 * @return
	 */
	public Flux<Payment> findByCreditId(String creditId);
	
	/**
	 * 
	 * @param customerId
	 * @return
	 */
	public Flux<Payment> findTop10ByCardNumberOrderByPaymentDateDesc(Long cardNumber);
	
}
