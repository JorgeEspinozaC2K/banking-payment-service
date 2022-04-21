package com.banking.payment.service.app.entity;

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
@Document(collection = "credits")
public class Credit {
	
	@Id
	private String id;
	
	private Boolean forCard = false;
	
	private String cardNumber;
	
	private String customerId;
	
	private String requestId;
	
	private Boolean fullyPaid = false;
	
	private Double totalLoan;
	
	private Double totalPaid;
	
	private Double remainingLoan;
	
	private Boolean activeLoan = false;
	
	private Boolean claimedLoan = false;
	
	private Double interestRate = 6.90;
	
	private Double interest;
	
	//month effective tax
	private Double met;
	
	//capital restoring factor
	private Double crf;
	
	private Integer actualQuota;
	
	private Integer totalQuotas;
	
	private Integer remainingQuotas;
	
	private Double nextQuotaAmount;
    
	private Double nextMinPaymentAmount;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fullyPaymentDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updateAt;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createAt;
}
