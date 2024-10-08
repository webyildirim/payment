package com.acme.payment.entity;

import java.util.List;

import com.acme.payment.enums.Provider;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "provider_method_unq_provider_method", columnNames = { "provider", "methodCode" }))
public class PaymentMethod {

	public PaymentMethod() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short id;
	@Column(length = 100)
	@Enumerated(EnumType.STRING)
	@NotNull
	private Provider provider;
	@NotNull
	private String methodCode;
	@NotNull
	private String serviceUrl;
	private Boolean refundable;
	private Short allowedInstallment;
	private String apiKey;
	private Short connectionTimeout;
	@OneToMany(mappedBy = "paymentMethod", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PaymentMethodConfiguration> configurations;
	
	public Short getId() {
		return id;
	}
	public void setId(Short id) {
		this.id = id;
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
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public Short getConnectionTimeout() {
		return connectionTimeout;
	}
	public void setConnectionTimeout(Short connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}
	public List<PaymentMethodConfiguration> getConfigurations() {
		return configurations;
	}
	public void setConfigurations(List<PaymentMethodConfiguration> configurations) {
		this.configurations = configurations;
	}
	
}
