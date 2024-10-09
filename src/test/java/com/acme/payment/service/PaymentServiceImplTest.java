package com.acme.payment.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.acme.payment.api.model.PostPaymentRequest;
import com.acme.payment.entity.Payment;
import com.acme.payment.enums.PaymentStatus;
import com.acme.payment.enums.PaymentType;
import com.acme.payment.repository.PaymentLogRepository;
import com.acme.payment.repository.PaymentRepository;
import com.acme.payment.service.impl.PaymentServiceImpl;
import com.acme.payment.service.provider.ProviderPaymentService;
import com.acme.payment.service.provider.ProviderServiceFactory;
import com.acme.payment.service.provider.model.InitiatePaymentContext;
import com.acme.payment.service.provider.model.PaymentResult;
import com.acme.payment.util.mapper.PaymentMapper;

public class PaymentServiceImplTest {

	@Mock
	private PaymentRepository paymentRepository;

	@Mock
	private ProviderServiceFactory providerServiceFactory;

	@Mock
	private PaymentLogRepository paymentLogRepository;

	@Mock
	private PaymentMapper paymentMapper;

	@Mock
	private ProviderPaymentService providerPaymentService;

	@InjectMocks
	private PaymentServiceImpl paymentService;

	@BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(providerServiceFactory.getProviderPaymentService(any())).thenReturn(providerPaymentService);
        when(paymentMapper.toEntity(any(PostPaymentRequest.class), any(PaymentType.class))).thenReturn(new Payment());
        when(paymentLogRepository.save(any())).thenReturn(null);
    }

	@Test
	void testPay_Success() {
		// Arrange
		PostPaymentRequest paymentRequest = new PostPaymentRequest();
		paymentRequest.setExternalReference("12345");
		paymentRequest.setAmount(BigDecimal.valueOf(100));
		paymentRequest.setCurrency("USD");

		PaymentResult paymentResult = new PaymentResult();
		paymentResult.setStatus(PaymentResult.ResponseStatus.CAPTURED);
		when(providerPaymentService.initiatePayment(any(InitiatePaymentContext.class))).thenReturn(paymentResult);
		Payment payment = new Payment();
		payment.setStatus(PaymentStatus.PENDING);
		when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

		// Act
		assertDoesNotThrow(() -> paymentService.pay(paymentRequest));

		// Assert
		verify(providerServiceFactory, times(1)).getProviderPaymentService(any());
		verify(providerPaymentService, times(1)).initiatePayment(any(InitiatePaymentContext.class));
		verify(paymentRepository, times(2)).save(any(Payment.class));
	}

	@Test
	void testPay_Failure() {
		// Arrange
		PostPaymentRequest paymentRequest = new PostPaymentRequest();
		paymentRequest.setExternalReference("12345");
		paymentRequest.setAmount(BigDecimal.valueOf(100));
		paymentRequest.setCurrency("USD");

		doThrow(new RuntimeException("Database error")).when(providerPaymentService)
				.initiatePayment(any(InitiatePaymentContext.class));

		// Act & Assert
		assertThrows(RuntimeException.class, () -> paymentService.pay(paymentRequest));
		verify(providerServiceFactory, times(1)).getProviderPaymentService(any());
		verify(providerPaymentService, times(1)).initiatePayment(any(InitiatePaymentContext.class));
	}
}
