package com.acme.payment.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.acme.payment.enums.PaymentStatus;
import com.acme.payment.enums.PaymentType;
import com.acme.payment.enums.Provider;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "payment_unq_uuid", columnNames = { "uuid" }))
public class Payment {

	public Payment() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Column(length = 100)
	@Enumerated(EnumType.STRING)
	private PaymentType type;
	@NotNull
	@Column(length = 100)
	@Enumerated(EnumType.STRING)
	private Provider provider;
	@NotNull
	private String method;
	private UUID uuid;
	private UUID originalPaymentId;
	@NotNull
	private String externalReference;
	private BigDecimal amount;
	private String currency;
	private String customer;
	@Enumerated(EnumType.STRING)
	private PaymentStatus status;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	@OneToMany
	private List<PaymentLog> logs;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PaymentType getType() {
		return type;
	}
	public void setType(PaymentType type) {
		this.type = type;
	}
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public UUID getOriginalPaymentId() {
		return originalPaymentId;
	}
	public void setOriginalPaymentId(UUID originalPaymentId) {
		this.originalPaymentId = originalPaymentId;
	}
	public String getExternalReference() {
		return externalReference;
	}
	public void setExternalReference(String reference) {
		this.externalReference = reference;
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
	public PaymentStatus getStatus() {
		return status;
	}
	public void setStatus(PaymentStatus status) {
		this.status = status;
	}
	public List<PaymentLog> getLogs() {
		return logs;
	}
	public void setLogs(List<PaymentLog> logs) {
		this.logs = logs;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
