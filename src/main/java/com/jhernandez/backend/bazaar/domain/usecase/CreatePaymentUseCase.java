package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.PaymentException;
import com.jhernandez.backend.bazaar.domain.model.Payment;

/*
 * Use case interface for creating a payment.
 */
public interface CreatePaymentUseCase {

    Payment createPayment(Payment payment) throws PaymentException;

}
