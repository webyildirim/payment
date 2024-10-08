package com.acme.payment.api.model;

import jakarta.validation.constraints.NotNull;

public class PaymentMetadata {

	@NotNull
	private String key;
	private String value;
	private Boolean pii;
	
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
