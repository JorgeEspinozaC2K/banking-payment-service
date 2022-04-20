package com.banking.payment.service.app.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Petition {
	
	@Id
	private String id;
	
	private String petitionUrl;
	
	private Object petition;
	
	private Date createAt;
	
}
