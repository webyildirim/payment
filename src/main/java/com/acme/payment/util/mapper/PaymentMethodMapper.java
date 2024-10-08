package com.acme.payment.util.mapper;

import java.util.ArrayList;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import com.acme.payment.entity.PaymentMethod;
import com.acme.payment.entity.PaymentMethodConfiguration;

@Component
public class PaymentMethodMapper {

	public PaymentMethod toEntity(com.acme.payment.api.model.PaymentMethod paymentMethod) {
		PaymentMethod paymentMethodEntity = new PaymentMethod();
		paymentMethodEntity.setProvider(paymentMethod.getProvider());
		paymentMethodEntity.setMethodCode(paymentMethod.getMethodCode());
		paymentMethodEntity.setServiceUrl(paymentMethod.getServiceUrl());
		paymentMethodEntity.setAllowedInstallment(paymentMethod.getAllowedInstallment());
		paymentMethodEntity.setApiKey(paymentMethod.getApiKey());
		paymentMethodEntity.setConnectionTimeout(paymentMethod.getConnectionTimeout());
		paymentMethodEntity.setRefundable(paymentMethod.getRefundable());
		return paymentMethodEntity;

	}

	public PaymentMethodConfiguration toEntity(com.acme.payment.api.model.PaymentMethodConfiguration paymentMethodConfiguration) {
		PaymentMethodConfiguration methodConfigurationEntity = new PaymentMethodConfiguration();
		methodConfigurationEntity.setKey(paymentMethodConfiguration.getKey());
		methodConfigurationEntity.setValue(paymentMethodConfiguration.getValue());
		methodConfigurationEntity.setPii(paymentMethodConfiguration.getPii());
		return methodConfigurationEntity;

	}

	public com.acme.payment.api.model.PaymentMethod fromEntityWithDetails(PaymentMethod paymentMethod) {
		com.acme.payment.api.model.PaymentMethod paymentMethodModel = fromEntity(paymentMethod);
		paymentMethod.setConfigurations(new ArrayList<>());
		for (PaymentMethodConfiguration srcConfig : CollectionUtils.emptyIfNull(paymentMethod.getConfigurations())) {
			com.acme.payment.api.model.PaymentMethodConfiguration configuration = new com.acme.payment.api.model.PaymentMethodConfiguration();
			configuration.setKey(srcConfig.getKey());
			configuration.setValue(srcConfig.getValue());
			configuration.setPii(srcConfig.getPii());
			paymentMethodModel.getConfigurations().add(configuration);
		}
		return paymentMethodModel;
	}

	public com.acme.payment.api.model.PaymentMethod fromEntity(PaymentMethod paymentMethod) {
		com.acme.payment.api.model.PaymentMethod paymentMethodModel = new com.acme.payment.api.model.PaymentMethod();
		paymentMethodModel.setProvider(paymentMethod.getProvider());
		paymentMethodModel.setMethodCode(paymentMethod.getMethodCode());
		paymentMethodModel.setServiceUrl(paymentMethod.getServiceUrl());
		paymentMethodModel.setAllowedInstallment(paymentMethod.getAllowedInstallment());
		paymentMethodModel.setApiKey(paymentMethod.getApiKey());
		paymentMethodModel.setConnectionTimeout(paymentMethod.getConnectionTimeout());
		paymentMethodModel.setRefundable(paymentMethod.getRefundable());
		return paymentMethodModel;
	}

}
