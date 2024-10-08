package com.acme.payment.service.provider.model;

import com.acme.payment.api.model.PaymentMethod;
import com.acme.payment.api.model.PostPaymentRequest;

public class InitiatePaymentContext {

	public final PostPaymentRequest request;
	public final PaymentMethod paymentMethod;
	
	public InitiatePaymentContext(PostPaymentRequest paymentReq, PaymentMethod paymentMethod) {
		super();
		this.request = paymentReq;
		this.paymentMethod = paymentMethod;
	}
	
}
