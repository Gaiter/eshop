package com.example.eshop.servise;

import com.example.eshop.entity.Payment;

import java.util.List;

public interface PaymentService {
    Payment get(Long id);

    List<Payment> getAll();

    void delete(Long id);

    Payment create(Payment order, Long orderId);

    void update(Payment order);
}
