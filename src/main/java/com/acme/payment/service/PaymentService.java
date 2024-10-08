package com.acme.payment.service;

import com.acme.payment.api.model.PostPaymentRequest;

public interface PaymentService {
	
	void pay(PostPaymentRequest paymentReq);
	

}
