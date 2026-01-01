package com.jhernandez.backend.bazaar.application.port;

import com.jhernandez.backend.bazaar.domain.model.Payment;

/*
 * Port interface for payment provider operations.
 */
public interface PaymentProviderPort {

    Payment createPayment(Payment payment);

}
