package com.example.eshop.dto;

import com.example.eshop.entity.Item;
import com.example.eshop.entity.Payment;
import com.example.eshop.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class OrderDto {
    private Long id;
    private OrderStatus orderStatus;
    private Set<Item> items;
    private Set<Payment> payments;
}
