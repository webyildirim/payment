package com.acme.payment.service;

import com.acme.payment.api.model.PaymentMethod;

public interface PaymentMethodService {
	
	
	void createPaymentMethod(PaymentMethod paymentMethod) ;
	void updatePaymentMethod(PaymentMethod paymentMethod);
	void deletePaymentMethod(String provider, String method);
	PaymentMethod getPaymentMethod(String provider, String method);
	

}
