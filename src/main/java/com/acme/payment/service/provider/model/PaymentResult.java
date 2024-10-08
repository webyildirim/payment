package com.acme.payment.service.provider.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.acme.payment.entity.Payment;

public class PaymentResult {
	
	private Payment payment;
	private String rawRequest;
	private String rawResponse;
	private Date txnTime = new Date();
	private ResponseStatus status;

	
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
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

	public ResponseStatus getStatus() {
		return status;
	}
	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public Date getTxnTime() {
		return txnTime;
	}
	public void setTxnTime(Date txnTime) {
		this.txnTime = txnTime;
	}


	public enum ResponseStatus {
		CAPTURED, ERROR, FRAUD, NA;

		private static Map<String, ResponseStatus> nameMap = new HashMap<>();
		static {
			for (ResponseStatus r : values()) {
				nameMap.put(r.name(), r);
			}
		}

		public static ResponseStatus fromName(String name) {
			return nameMap.get(name);
		}
	}
}
