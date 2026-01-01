package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import com.jhernandez.backend.bazaar.application.port.PaymentServicePort;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.PaymentRequestDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.PaymentDtoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.PAYMENTS;

/*
 * Controller for payment-related endpoints.
 */
@RestController
@RequestMapping(PAYMENTS)
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentServicePort paymentService;
    private final PaymentDtoMapper paymentDtoMapper;

    @PostMapping
    public ResponseEntity<?> createPayment(@RequestBody PaymentRequestDto paymentRequest) {
        log.info("Received payment request: {} {}", paymentRequest.getAmount(), paymentRequest.getCurrency());
        return ResponseEntity.ok(paymentDtoMapper.toDto(paymentService.createPayment(paymentDtoMapper.toDomain(paymentRequest))));
    }

}
