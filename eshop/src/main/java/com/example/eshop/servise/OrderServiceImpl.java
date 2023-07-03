package com.example.eshop.servise;

import com.example.eshop.entity.Item;
import com.example.eshop.entity.Order;
import com.example.eshop.entity.Payment;
import com.example.eshop.enums.EventType;
import com.example.eshop.enums.OrderStatus;
import com.example.eshop.exception.NotFoundException;
import com.example.eshop.exception.WrongStatusException;
import com.example.eshop.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private EventService eventService;

    @Override
    public Order get(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException());
        eventService.sendEvent(EventType.GET, Order.class.getName());
        return order;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = orderRepository.findAll();
        eventService.sendEvent(EventType.GETALL, Order.class.getName());
        return orders;
    }

    @Override
    public void delete(Long id) {
        eventService.sendEvent(EventType.DELETE, Order.class.getName());
        orderRepository.deleteById(id);
    }

    @Override
    public Order create(Order order) {
        if (order.getOrderStatus() == OrderStatus.SHIPPING || order.getOrderStatus() == OrderStatus.DELIVERED) {
            throw new WrongStatusException();
        }
        Order orderDb = orderRepository.saveAndFlush(order);
        eventService.sendEvent(EventType.CREATE, Order.class.getName());
        return orderDb;
    }

    @Override
    public void update(Order order) {
        Order orderDb = orderRepository.getOneWithPaymentsAndOrdersById(order.getId()).orElseThrow(() -> new NotFoundException());
        if (isItemsPayed(orderDb) & (order.getOrderStatus() == OrderStatus.SHIPPING
                || order.getOrderStatus() == OrderStatus.DELIVERED))
            throw new WrongStatusException();
        orderRepository.saveAndFlush(order);
        eventService.sendEvent(EventType.UPDATE, Order.class.getName());
    }

    private boolean isItemsPayed(Order order) {
        Long itemCostSum = order.getItems()
                .stream()
                .mapToLong(Item::getPrice)
                .sum();
        Long paymentSum = order.getPayments()
                .stream()
                .mapToLong(Payment::getSum)
                .sum();
        return !itemCostSum.equals(paymentSum);
    }
}
