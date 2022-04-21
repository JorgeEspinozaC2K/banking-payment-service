package com.banking.payment.service.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.payment.service.app.model.Payment;
import com.banking.payment.service.app.service.PaymentService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/payments")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping()
	public Flux<Payment> index(){
		return paymentService.findAllPyments();
	}
	
	@GetMapping("/cid/{id}")
	public Flux<Payment> searchByCreditId(@PathVariable String id){
		return paymentService.findPaymentByCreditId(id);
	}
	
	@GetMapping("/{id}")
	public Mono<Payment> searchPaymentById(@PathVariable String id){
		return paymentService.findPaymentById(id);
	}
	
	@PostMapping("/save")
	public Mono<Payment> save(@RequestBody Payment payment){
		return paymentService.savePayment(payment);
	}
	
	@DeleteMapping("/delete")
	public Mono<Void> delete(@RequestBody Payment payment){
		return paymentService.deletePayment(payment);
	}
}
