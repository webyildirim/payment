package com.acme.payment.service.impl;

import com.acme.payment.api.model.PostPaymentRequest;
import com.acme.payment.entity.Payment;
import com.acme.payment.entity.PaymentLog;
import com.acme.payment.enums.PaymentStatus;
import com.acme.payment.repository.PaymentLogRepository;
import com.acme.payment.repository.PaymentRepository;
import com.acme.payment.service.PaymentService;
import com.acme.payment.service.provider.model.PaymentResult;

public abstract class BasePaymentService implements PaymentService {

	private final PaymentLogRepository paymentLogRepository;
	private final PaymentRepository paymentRepository;

	public BasePaymentService(PaymentLogRepository paymentLogRepository, PaymentRepository paymenRepository) {
		this.paymentLogRepository = paymentLogRepository;
		this.paymentRepository = paymenRepository;
	}

	abstract PaymentResult savePaymentAndCallProvider(PostPaymentRequest paymentReq);
	
	public void pay(PostPaymentRequest paymentReq) {
		if (!validate(paymentReq)) {
			// TODO: Response accordingly, no need to continue
			return;
		}
		PaymentResult result = savePaymentAndCallProvider(paymentReq);
		saveStatus(result);
		paymentLogRepository.save(createPaymentLog(result));
	}
	
	private void saveStatus(PaymentResult result) {
		Payment payment = result.getPayment();
		PaymentStatus status = mapPaymentStatus(result);
		if (!payment.getStatus().equals(status)) {
			payment.setStatus(status);
			paymentRepository.save(payment);
		}
	}

	protected PaymentStatus mapPaymentStatus(PaymentResult result) {
		switch (result.getStatus()) {
		case CAPTURED: 
			return PaymentStatus.SUCCESS;
		case FRAUD: 
			return PaymentStatus.FRAUD;
		case ERROR: 
		case NA:
			return PaymentStatus.FAILED;
		default:
			throw new IllegalArgumentException("Unexpected value: " + result.getStatus());
		}
	}

	protected boolean validate(PostPaymentRequest paymentReq) {
		// TODO: validate request if needed
		return !isFraud(paymentReq);
	}

	protected boolean isFraud(PostPaymentRequest paymentReq) {
		return paymentRepository.findByExternalReferenceAndStatus(paymentReq.getExternalReference(), PaymentStatus.FRAUD) != null;
	}

	protected PaymentLog createPaymentLog(PaymentResult result) {
		PaymentLog paymentLog = new PaymentLog();
		paymentLog.setPayment(result.getPayment());
		paymentLog.setOperation("myOperationCallTowardsZiraatV2");
		paymentLog.setRawRequest(result.getRawRequest());
		paymentLog.setRawResponse(result.getRawResponse());
		paymentLog.setTxnTime(result.getTxnTime());
		return paymentLog;
	}
}
