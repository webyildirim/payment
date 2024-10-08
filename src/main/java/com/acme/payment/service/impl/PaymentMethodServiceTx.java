package com.acme.payment.service.impl;

import org.springframework.stereotype.Service;

import com.acme.payment.api.model.PaymentMethodConfiguration;
import com.acme.payment.entity.PaymentMethod;
import com.acme.payment.repository.PaymentMethodConfigurationRepository;
import com.acme.payment.repository.PaymentMethodRepository;
import com.acme.payment.util.mapper.PaymentMethodMapper;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class PaymentMethodServiceTx {

	private final PaymentMethodRepository paymentMethodRepository;
	private final  PaymentMethodConfigurationRepository paymentMethodConfigurationRepository;
	private final PaymentMethodMapper paymentMethodMapper;

	public PaymentMethodServiceTx(PaymentMethodRepository paymentMethodRepository,
			PaymentMethodConfigurationRepository paymentMethodConfigurationRepository,
			PaymentMethodMapper paymentMethodMapper) {
		super();
		this.paymentMethodRepository = paymentMethodRepository;
		this.paymentMethodConfigurationRepository = paymentMethodConfigurationRepository;
		this.paymentMethodMapper = paymentMethodMapper;
	}


	public void mapAndSavePaymentMethod(com.acme.payment.api.model.PaymentMethod paymentMethod) {
		PaymentMethod methodEntity = paymentMethodMapper.toEntity(paymentMethod);
		paymentMethodRepository.save(methodEntity);
		for (PaymentMethodConfiguration config : paymentMethod.getConfigurations()) {
			com.acme.payment.entity.PaymentMethodConfiguration configEntity = paymentMethodMapper.toEntity(config);
			configEntity.setPaymentMethod(methodEntity);
			paymentMethodConfigurationRepository.save(configEntity);
		}
	}

}
