package com.acme.payment.service.provider;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.acme.payment.enums.Provider;
import com.acme.payment.service.provider.annotation.PaymentServiceProvider;

@Component
public class ProviderServiceFactory implements ApplicationContextAware, InitializingBean {
    private Map<Provider, ProviderPaymentService> providerServiceMap = new HashMap<>(); 

    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {

        Map<String, Object> planningAnnotatedBeans = applicationContext.getBeansWithAnnotation(PaymentServiceProvider.class); 

        for (Map.Entry<String, Object> beanEntry : planningAnnotatedBeans.entrySet()) { 
        	ProviderPaymentService planningService = (ProviderPaymentService) beanEntry.getValue(); 
            Provider provider = planningService.getClass().getAnnotation(PaymentServiceProvider.class).code(); 
            providerServiceMap.put(provider, planningService); 
        }
    }

    public ProviderPaymentService getProviderPaymentService(Provider provider) {
        return providerServiceMap.get(provider); 
    }

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
        this.applicationContext = applicationContext;
		
	}
}