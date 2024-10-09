package com.acme.payment.api.model;

import java.util.List;

import com.acme.payment.enums.Provider;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;

public class PaymentMethod {

	@NotNull
	private Provider provider;
	@NotNull
	private String methodCode;
	@NotNull
	private String serviceUrl;
	private Short connectionTimeout;
	private String apiKey;
	@JsonIgnore
	private String password;
	private Boolean refundable;
	private Short allowedInstallment;
	private List<PaymentMethodConfiguration> configurations;

	public PaymentMethod() {
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

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public Short getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(Short connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getRefundable() {
		return refundable;
	}

	public void setRefundable(Boolean refundable) {
		this.refundable = refundable;
	}

	public Short getAllowedInstallment() {
		return allowedInstallment;
	}

	public void setAllowedInstallment(Short allowedInstallment) {
		this.allowedInstallment = allowedInstallment;
	}

	public List<PaymentMethodConfiguration> getConfigurations() {
		return configurations;
	}

	public void setConfigurations(List<PaymentMethodConfiguration> configurations) {
		this.configurations = configurations;
	}
	
	
}
