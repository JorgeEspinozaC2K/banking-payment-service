package com.banking.payment.service.app.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "payments")
public class Payment {
	
	@Id
	private String id;
	
	private Boolean extern;
	
	private String bank = "This bank";
	
	private String creditId;
	
	private Long cardNumber;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate paymentDate = LocalDate.now();
	
	private Integer quota;
	
	private Double paidAmount;
	
}
