package com.acme.payment.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.payment.entity.Payment;
import com.acme.payment.enums.PaymentStatus;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	Payment findByUuid(UUID uuid);
	Payment findByExternalReferenceAndStatus(String externalReference, PaymentStatus status);
}
