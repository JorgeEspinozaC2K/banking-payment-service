package com.banking.payment.service.app.model;

import java.util.Date;

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
	
	private String creditId;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date paymentDate;
	
	private Integer quota;
	
	private Double quotaAmount;
	
	private Double minPaymentAmount;
	
	private Double paidAmount;
	
	private Double interestAmount;
	
	private Double totalAmount;
	
	private Double remainingAmount;
	
	private Integer remainingQuotas;
	
}
