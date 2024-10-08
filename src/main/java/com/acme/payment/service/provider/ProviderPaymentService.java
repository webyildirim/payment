package com.acme.payment.service.provider;

import com.acme.payment.service.provider.model.InitiatePaymentContext;
import com.acme.payment.service.provider.model.PaymentResult;

public interface ProviderPaymentService {

	abstract PaymentResult initiatePayment(InitiatePaymentContext context);
	
	default PaymentResult initiatePaymentWithMonitoring(InitiatePaymentContext context) {
		PaymentResult out = null;
		try {
			out = initiatePayment(context);
			return out;
		} catch (Exception e) {
			// trigger some event for monitoring
			throw e;
		} finally {
			if (out != null && (PaymentResult.ResponseStatus.ERROR.equals(out.getStatus()))) {
				// trigger some event for monitoring provider communication / response
			}
		}
	}
}
