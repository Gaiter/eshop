package com.example.eshop.mapper;

import com.example.eshop.dto.PaymentDto;
import com.example.eshop.entity.Payment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentDto toDto(Payment payment);

    Payment toEntity(PaymentDto paymentDto);

    List<PaymentDto> toDto(List<Payment> payments);

    List<Payment> toEntity(List<PaymentDto> paymentDtos);
}
