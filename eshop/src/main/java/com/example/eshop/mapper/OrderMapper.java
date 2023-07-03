package com.example.eshop.mapper;

import com.example.eshop.dto.OrderDto;
import com.example.eshop.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
uses = {ItemMapper.class, PaymentMapper.class})
public interface OrderMapper {
    OrderDto toDto(Order order);

    Order toEntity(OrderDto orderDto);

    List<OrderDto> toDto(List<Order> orders);

    List<Order> toEntity(List<OrderDto> orderDtos);
}
