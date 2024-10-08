package com.acme.payment.api.controller.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.payment.api.model.PaymentMethod;
import com.acme.payment.service.PaymentMethodService;

@RestController
@RequestMapping("${api.version}/management/payment-method")
public class PaymentMethodRestService {

	@Autowired
	public PaymentMethodService paymentMethodService;

	@PostMapping()
	public ResponseEntity<Void> createPaymentMethod(@RequestBody PaymentMethod paymentMethod) {
		paymentMethodService.createPaymentMethod(paymentMethod);
		// TODO: Consider to return the GET url of the created/updated source 
		return ResponseEntity.created(null).build();

	}

	@GetMapping("{provider}/{methodCode}")
	public ResponseEntity<PaymentMethod> getPaymentMethod(@PathVariable String provider, @PathVariable String methodCode) {
		return ResponseEntity.ok(paymentMethodService.getPaymentMethod(provider, methodCode));
	}

	@PutMapping
	public ResponseEntity<Void> updatePaymentMethod(@RequestBody PaymentMethod paymentMethod) {
		// TODO: Consider to return the GET url of the created/updated source
		paymentMethodService.updatePaymentMethod(paymentMethod);
		return ResponseEntity.accepted().build();
	}
}
