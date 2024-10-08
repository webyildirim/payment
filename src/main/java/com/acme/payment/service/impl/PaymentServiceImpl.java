package com.acme.payment.service.impl;

import org.springframework.stereotype.Service;

import com.acme.payment.api.model.PaymentMethod;
import com.acme.payment.api.model.PostPaymentRequest;
import com.acme.payment.entity.Payment;
import com.acme.payment.entity.PaymentLog;
import com.acme.payment.enums.PaymentType;
import com.acme.payment.repository.PaymentLogRepository;
import com.acme.payment.repository.PaymentRepository;
import com.acme.payment.service.PaymentService;
import com.acme.payment.service.provider.ProviderPaymentService;
import com.acme.payment.service.provider.ProviderServiceFactory;
import com.acme.payment.service.provider.model.InitiatePaymentContext;
import com.acme.payment.service.provider.model.PaymentResult;
import com.acme.payment.util.mapper.PaymentMapper;

@Service
public class PaymentServiceImpl extends BasePaymentService implements PaymentService {

	private final PaymentRepository paymentRepository;
	private final ProviderServiceFactory providerServiceFactory;
	private final PaymentMapper paymentMapper;
	
	public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentLogRepository paymentLogRepository, ProviderServiceFactory providerServiceFactory, PaymentMapper paymentMapper) {
		super(paymentLogRepository, paymentRepository);
		this.paymentRepository = paymentRepository;
		this.providerServiceFactory = providerServiceFactory;
		this.paymentMapper = paymentMapper;
	}


	@Override
	public PaymentResult savePaymentAndCallProvider(PostPaymentRequest paymentReq) {
		ProviderPaymentService providerService = providerServiceFactory.getProviderPaymentService(paymentReq.getProvider());
		Payment payment = null;
		if (paymentReq.getUuid() != null) {
			payment = paymentRepository.findByUuid(paymentReq.getUuid());
		}
		if (payment == null) {
			payment = paymentMapper.toEntity(paymentReq, PaymentType.PAYMENT);
			payment = paymentRepository.save(payment);
		}
		
		PaymentMethod method = null; // TODO: retrieve paymentMethod model from cache2k via localCacheService.getPaymentMethod(provider,method) for configuration purpose
		InitiatePaymentContext context = new InitiatePaymentContext(paymentReq, method);
		PaymentResult result = providerService.initiatePayment(context);
		result.setPayment(payment);
		return result;
	}


}
