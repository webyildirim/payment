package com.acme.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.payment.entity.PaymentLog;

@Repository
public interface PaymentLogRepository extends JpaRepository<PaymentLog, Long> {
}
