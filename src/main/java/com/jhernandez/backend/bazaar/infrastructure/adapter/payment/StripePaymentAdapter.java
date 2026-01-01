package com.jhernandez.backend.bazaar.infrastructure.adapter.payment;

import org.springframework.stereotype.Service;

import com.jhernandez.backend.bazaar.application.port.PaymentProviderPort;
import com.jhernandez.backend.bazaar.domain.exception.PaymentException;
import com.jhernandez.backend.bazaar.domain.model.ErrorCode;
import com.jhernandez.backend.bazaar.domain.model.Payment;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import lombok.extern.slf4j.Slf4j;

/*
 * Adapter class for handling payments using Stripe.
 * Implements the PaymentProviderPort interface.
 */
@Service
@Slf4j
public class StripePaymentAdapter implements PaymentProviderPort {

    @Override
    public Payment createPayment(Payment payment) throws PaymentException {
        log.info("Creating payment: {} {}", payment.getAmount(), payment.getCurrency());
        Double amount = payment.getAmount() * 100;
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amount.longValue())
                .setCurrency(payment.getCurrency())
                .build();

            PaymentIntent intent;
            try {
                intent = PaymentIntent.create(params);
            } catch (StripeException e) {
                log.error("Stripe payment intent creation failed: {}", e.getMessage());
                throw new PaymentException(ErrorCode.PAYMENT_INTENT_FAILED);
            }
            payment.setClientSecret(intent.getClientSecret());
            return payment;
    }

}
