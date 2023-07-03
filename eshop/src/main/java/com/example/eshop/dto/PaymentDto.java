package com.example.eshop.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentDto {
    private Long id;
    private Long sum;
    private LocalDateTime dateTime;
}
