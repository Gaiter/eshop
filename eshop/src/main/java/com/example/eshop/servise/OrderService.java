package com.example.eshop.servise;

import com.example.eshop.entity.Order;

import java.util.List;

public interface OrderService {
    Order get(Long id);

    List<Order> getAll();

    void delete(Long id);

    Order create(Order order);

    void update(Order order);

}
