package com.example.eshop.mapper;

import com.example.eshop.dto.ItemDto;
import com.example.eshop.dto.OrderDto;
import com.example.eshop.entity.Item;
import com.example.eshop.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemDto toDto(Item item);

    Item toEntity(ItemDto itemDto);

    List<ItemDto> toDto(List<Item> items);

    List<Item> toEntity(List<ItemDto> itemDtos);
}
