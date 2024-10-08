package com.acme.payment.util.mapper;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.acme.payment.entity.Payment;
import com.acme.payment.enums.PaymentStatus;
import com.acme.payment.enums.PaymentType;

@Component
public class PaymentMapper {

	public Payment toEntity(com.acme.payment.api.model.PostPaymentRequest paymentReq, PaymentType type) {
		Payment payment = new Payment();
		payment.setProvider(paymentReq.getProvider());
		payment.setMethod(paymentReq.getMethodCode());
		payment.setUuid(paymentReq.getUuid() != null ? paymentReq.getUuid() : UUID.randomUUID());
		payment.setExternalReference(paymentReq.getExternalReference());
		payment.setAmount(paymentReq.getAmount());
		payment.setCurrency(paymentReq.getCurrency());
		payment.setOriginalPaymentId(paymentReq.getOriginalPaymentId());
		payment.setCreateDate(new Date());
		payment.setStatus(PaymentStatus.INITIALIZED);
		payment.setType(type);
		return payment;

	}
}
