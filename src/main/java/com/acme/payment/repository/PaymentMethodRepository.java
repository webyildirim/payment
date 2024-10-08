package com.acme.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.payment.entity.PaymentMethod;
import com.acme.payment.enums.Provider;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Short> {

	PaymentMethod findByProviderAndMethodCode(Provider provider, String methodCode);

}
