package com.banking.payment.service.app.service.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.payment.service.app.entity.Credit;
import com.banking.payment.service.app.model.Payment;
import com.banking.payment.service.app.repository.PaymentRepository;
import com.banking.payment.service.app.service.PaymentService;
import com.banking.payment.service.app.webclient.PaymentWebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
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
		return paymentRepository.findById(id)
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
	public Mono<Payment> savePayment(Payment payment) {
		
		return paymentWebClient.findCredit(payment.getCreditId())
				.defaultIfEmpty(new Credit())
				.flatMap(_credit ->{
					if (_credit.getId() == null) {
						return Mono.error(new InterruptedException("Request failed credit with ID: " 
					+ payment.getCreditId() + " does not exist."));
					} else {
						_credit.setRemainingLoan(_credit.getRemainingLoan()-payment.getPaidAmount());
						payment.setQuota(_credit.getActualQuota()+1);
						_credit.setActualQuota(payment.getQuota());
						_credit.setRemainingQuotas(_credit.getRemainingQuotas()-1);
						payment.setQuota(_credit.getActualQuota());
						return paymentWebClient.saveCredit(_credit)
								.defaultIfEmpty(new Credit())
								.flatMap(_cr ->{
									if (_cr.getId() == null) {
										return Mono.error(new InterruptedException("Error al actualizar el credito"));
									}
									return paymentRepository.save(payment)
											.defaultIfEmpty(new Payment())
											.flatMap(_pay ->{
												if(_pay == null) {
													return Mono.error(new InterruptedException("Error at payment save"));
												}
												return paymentRepository.save(payment);
											});
								}).onErrorResume(_ex->{
									log.error(_ex.getMessage());
									return Mono.empty();
								});
						}});
	}

	@Override
	public Mono<Void> deletePayment(Payment payment) {
		return paymentRepository.delete(payment);
	}
	
}
