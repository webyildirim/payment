package com.acme.payment.api.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.acme.payment.enums.Provider;

import jakarta.validation.constraints.NotNull;

public class PostPaymentRequest {

	@NotNull
	private Provider provider;
	@NotNull
	private String methodCode;
	@NotNull
	private UUID uuid;
	@NotNull
	private String externalReference;
	private UUID originalPaymentId;
	private BigDecimal amount;
	private String currency;
	private String customer;
	private List<PaymentMetadata> metadata;

	public PostPaymentRequest() {
		super();
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public String getMethodCode() {
		return methodCode;
	}

	public void setMethodCode(String methodCode) {
		this.methodCode = methodCode;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getExternalReference() {
		return externalReference;
	}

	public void setExternalReference(String externalReference) {
		this.externalReference = externalReference;
	}

	public UUID getOriginalPaymentId() {
		return originalPaymentId;
	}

	public void setOriginalPaymentId(UUID originalPaymentId) {
		this.originalPaymentId = originalPaymentId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public List<PaymentMetadata> getMetadata() {
		return metadata;
	}

	public void setMetadata(List<PaymentMetadata> metadata) {
		this.metadata = metadata;
	}
	
	
}
