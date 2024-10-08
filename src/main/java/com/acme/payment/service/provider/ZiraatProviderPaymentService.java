package com.acme.payment.service.provider;

import org.springframework.stereotype.Component;

import com.acme.payment.enums.Provider;
import com.acme.payment.service.provider.annotation.PaymentServiceProvider;
import com.acme.payment.service.provider.client.ZiraatSoapClient;
import com.acme.payment.service.provider.model.InitiatePaymentContext;
import com.acme.payment.service.provider.model.PaymentResult;
import com.acme.payment.service.provider.model.PaymentResult.ResponseStatus;

@Component
@PaymentServiceProvider(code = Provider.ZIRAAT)
public class ZiraatProviderPaymentService implements ProviderPaymentService {

	private final ZiraatSoapClient client;
	
	public ZiraatProviderPaymentService(ZiraatSoapClient client) {
		super();
		this.client = client;
	}

	@Override
	public PaymentResult initiatePayment(InitiatePaymentContext context) {
		PaymentResult result = new PaymentResult();
		// handle client related stuff, eventually obtain rawRequest and rawResponse as wrapping in result object
		result.setRawRequest("myildirimRequest");
		result.setRawResponse("{my : jsonRawResponse}");
		result.setStatus(mapStatus(context.request.getExternalReference()));
		return result;
	}

	private ResponseStatus mapStatus(String externalReference) {
		switch (externalReference) {
		case "@failed":
			return ResponseStatus.ERROR;
		case "@succcess":
			return ResponseStatus.CAPTURED;
		case "@fraud":
			return ResponseStatus.FRAUD;
		default:
			break;
		}
		return ResponseStatus.NA;
	}

}
