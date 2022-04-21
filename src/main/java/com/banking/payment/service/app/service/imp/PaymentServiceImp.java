package com.banking.payment.service.app.service.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.banking.payment.service.app.entity.Credit;
import com.banking.payment.service.app.model.Payment;
import com.banking.payment.service.app.repository.PaymentRepository;
import com.banking.payment.service.app.service.PaymentService;
import com.banking.payment.service.app.webclient.PaymentWebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PaymentServiceImp implements PaymentService {
	
	private static final Logger log = LoggerFactory.getLogger(PaymentServiceImp.class);
	
	private PaymentWebClient paymentWebClient = new PaymentWebClient();
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Override
	public Flux<Payment> findAllPyments() {
		return paymentRepository.findAll();
	}

	@Override
	public Flux<Payment> findPaymentByCreditId(String creditId) {
		return paymentRepository.findByCreditId(creditId)
				.defaultIfEmpty(new Payment())
				.flatMap(_payment ->{
					if (_payment.getId()==null) {
						return Mono.error(new InterruptedException("Payment doesn't exist"));
					}else {
						return Mono.just(_payment);
					}
				})
				.onErrorResume(_ex ->{
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Mono<Payment> findPaymentById(String id) {
		return paymentRepository.findById(id);
	}

	@Override
	public Mono<Payment> savePayment(Payment payment) {
		
		return paymentWebClient.findCredit(payment.getCreditId())
				.defaultIfEmpty(new Credit())
				.flatMap(_credit ->{
					if (_credit.getId() == null) {
						return Mono.error(new InterruptedException("Request failed credit with ID: " 
					+ payment.getCreditId() + " does not exist."));
					}else {
						return Mono.just(payment);
					}
				});
	}

	@Override
	public Mono<Void> deletePayment(Payment payment) {
		return paymentRepository.delete(payment);
	}
	
}
