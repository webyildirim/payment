package com.acme.payment.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class PaymentLog {

	public PaymentLog() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Payment payment;
	private String operation;
	private String rawRequest;
	private String rawResponse;
	@Temporal(TemporalType.TIMESTAMP)
	private Date txnTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getRawRequest() {
		return rawRequest;
	}
	public void setRawRequest(String rawRequest) {
		this.rawRequest = rawRequest;
	}
	public String getRawResponse() {
		return rawResponse;
	}
	public void setRawResponse(String rawResponse) {
		this.rawResponse = rawResponse;
	}
	public Date getTxnTime() {
		return txnTime;
	}
	public void setTxnTime(Date txnTime) {
		this.txnTime = txnTime;
	}
	
}
