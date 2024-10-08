package com.acme.payment.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.payment.api.model.PostPaymentRequest;
import com.acme.payment.service.PaymentService;

@RestController
@RequestMapping("${api.version}/pay")
public class PaymentRestService {

	@Autowired
	public PaymentService paymentService;

	@PostMapping()
	public ResponseEntity<Void> pay(@RequestBody PostPaymentRequest paymentReq) {
		paymentService.pay(paymentReq);
		return ResponseEntity.created(null).build();

	}
}
