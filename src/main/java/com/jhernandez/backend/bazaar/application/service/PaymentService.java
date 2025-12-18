package com.jhernandez.backend.bazaar.application.service;

import com.jhernandez.backend.bazaar.application.port.PaymentProviderPort;
import com.jhernandez.backend.bazaar.application.port.PaymentServicePort;
import com.jhernandez.backend.bazaar.domain.exception.PaymentException;
import com.jhernandez.backend.bazaar.domain.model.ErrorCode;
import com.jhernandez.backend.bazaar.domain.model.Payment;

/*
 * Service class for payment operations.
 */
public class PaymentService implements PaymentServicePort {

    private final PaymentProviderPort paymentProvider;

    public PaymentService(PaymentProviderPort paymentProvider) {
        this.paymentProvider = paymentProvider;
    }

    @Override
    public Payment createPayment(Payment payment) throws PaymentException {
        if (payment.getAmount() == null || payment.getAmount() <= 0) {
            throw new PaymentException(ErrorCode.PAYMENT_AMOUNT_INVALID);
        }
        if (payment.getCurrency() == null) {
            throw new PaymentException(ErrorCode.PAYMENT_CURRENCY_INVALID);
        }
        return paymentProvider.createPayment(payment);
    }

}
