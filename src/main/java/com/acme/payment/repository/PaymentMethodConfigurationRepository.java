package com.acme.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.payment.entity.PaymentMethodConfiguration;

@Repository
public interface PaymentMethodConfigurationRepository extends JpaRepository<PaymentMethodConfiguration, Short>{

}
