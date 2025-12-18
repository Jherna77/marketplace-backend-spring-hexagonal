package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Payment;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.PaymentRequestDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.PaymentResponseDto;

/*
 * Mapper interface for converting Payment domain models to PaymentRequestDto and PaymentResponseDto objects.
 * Uses MapStruct for automatic mapping generation.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentDtoMapper {

    @Mapping(target = "clientSecret", ignore = true)
    Payment toDomain(PaymentRequestDto paymentRequestDto);

    PaymentResponseDto toDto(Payment payment);

}
