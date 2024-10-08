package com.acme.payment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class PaymentMethodConfiguration {

	public PaymentMethodConfiguration() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short id;
	@ManyToOne()
	private PaymentMethod paymentMethod;
	@NotNull
	private String key;
	@NotNull
	private String value;
	private Boolean pii; // TODO PII fields' values must be masked in logs and other unauthorized displaying channels
	
	public Short getId() {
		return id;
	}
	public void setId(Short id) {
		this.id = id;
	}
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Boolean getPii() {
		return pii;
	}
	public void setPii(Boolean pii) {
		this.pii = pii;
	}

	
}
