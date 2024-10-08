package com.acme.payment.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.acme.payment.api.model.PaymentMethodConfiguration;
import com.acme.payment.entity.PaymentMethod;
import com.acme.payment.enums.ErrorCode;
import com.acme.payment.enums.Provider;
import com.acme.payment.exception.EntityAlreadyExistException;
import com.acme.payment.exception.RecordNotFoundExceptioın;
import com.acme.payment.repository.PaymentMethodRepository;
import com.acme.payment.service.PaymentMethodService;
import com.acme.payment.util.mapper.PaymentMethodMapper;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

	private final PaymentMethodServiceTx paymentServiceTx;
	private final PaymentMethodRepository paymentMethodRepository;
	private final PaymentMethodMapper paymentMethodMapper;

	public PaymentMethodServiceImpl(PaymentMethodServiceTx paymentServiceTx,
			PaymentMethodRepository paymentMethodRepository, PaymentMethodMapper paymentMethodMapper) {
		super();
		this.paymentServiceTx = paymentServiceTx;
		this.paymentMethodRepository = paymentMethodRepository;
		this.paymentMethodMapper = paymentMethodMapper;
	}

	@Override
	public void createPaymentMethod(com.acme.payment.api.model.PaymentMethod paymentMethod) {
		PaymentMethod methodEntity = paymentMethodRepository.findByProviderAndMethodCode(paymentMethod.getProvider(),
				paymentMethod.getMethodCode());
		if (methodEntity != null) {
			throw new EntityAlreadyExistException(ErrorCode.PAYMENT_METHOD_ALLREADY_EXIST);
		}
		paymentServiceTx.mapAndSavePaymentMethod(paymentMethod);
	}

	@Override
	public void updatePaymentMethod(com.acme.payment.api.model.PaymentMethod paymentMethod) {
		PaymentMethod methodEntity = paymentMethodRepository.findByProviderAndMethodCode(paymentMethod.getProvider(),
				paymentMethod.getMethodCode());
		if (methodEntity == null) {
			paymentServiceTx.mapAndSavePaymentMethod(paymentMethod);
			return;
		}
		mapPaymentMethod(methodEntity, paymentMethod);
		if (methodEntity.getConfigurations() == null) {
			methodEntity.setConfigurations(new ArrayList<>());
		}
		for (PaymentMethodConfiguration requestedConfiguration : CollectionUtils.emptyIfNull(paymentMethod.getConfigurations())) {
			Optional<com.acme.payment.entity.PaymentMethodConfiguration> foundConfigOpt = methodEntity.getConfigurations().stream().filter(c -> c.getKey().equals(requestedConfiguration.getKey())).findAny();
			if (foundConfigOpt.isPresent()) {
				foundConfigOpt.get().setValue(requestedConfiguration.getValue());
				foundConfigOpt.get().setPii(requestedConfiguration.getPii());
			} else {
				com.acme.payment.entity.PaymentMethodConfiguration config = new com.acme.payment.entity.PaymentMethodConfiguration();
				config.setKey(requestedConfiguration.getKey());
				config.setValue(requestedConfiguration.getValue());
				config.setPii(requestedConfiguration.getPii());
				config.setPaymentMethod(methodEntity);
				methodEntity.getConfigurations().add(config);
			}
		}
		paymentMethodRepository.save(methodEntity);
	}

	private void mapPaymentMethod(PaymentMethod methodEntity, com.acme.payment.api.model.PaymentMethod paymentMethod) {
		// those mappings can be also done via a Mapper library
		methodEntity.setApiKey(paymentMethod.getApiKey());
		methodEntity.setServiceUrl(paymentMethod.getServiceUrl());
		methodEntity.setConnectionTimeout(paymentMethod.getConnectionTimeout());
		methodEntity.setRefundable(paymentMethod.getRefundable());
		methodEntity.setAllowedInstallment(paymentMethod.getAllowedInstallment());
	}

	@Override
	public void deletePaymentMethod(String provider, String method) {
		// TODO Auto-generated method stub

	}

	@Override
	public com.acme.payment.api.model.PaymentMethod getPaymentMethod(String providerCode, String method) {

		Provider provider = Provider.fromName(providerCode);
		if (provider == null) {
			throw new RecordNotFoundExceptioın();
		}

		PaymentMethod paymentMethod = paymentMethodRepository.findByProviderAndMethodCode(provider, method);
		if (paymentMethod == null) {
			throw new RecordNotFoundExceptioın();
		}

		return paymentMethodMapper.fromEntity(paymentMethod);
	}

}
