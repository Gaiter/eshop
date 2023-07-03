package com.example.eshop.servise;

import com.example.eshop.dto.ItemDto;
import com.example.eshop.entity.Item;
import com.example.eshop.entity.Order;
import com.example.eshop.enums.EventType;
import com.example.eshop.enums.OrderStatus;
import com.example.eshop.exception.NotFoundException;
import com.example.eshop.exception.UnprocessableEntityException;
import com.example.eshop.exception.WrongStatusException;
import com.example.eshop.mapper.ItemMapper;
import com.example.eshop.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;
    private OrderService orderService;
    private EventService eventService;
    private ItemMapper itemMapper;

    @Override
    public ItemDto get(Long id) {
        ItemDto itemDto = itemMapper.toDto(itemRepository.findById(id).orElseThrow(NotFoundException::new));
        eventService.sendEvent(EventType.GET, Item.class.getName());
        return itemDto;
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = itemRepository.findAll();
        eventService.sendEvent(EventType.GETALL, Item.class.getName());
        return items;
    }

    @Override
    public void delete(Long id) {
        eventService.sendEvent(EventType.DELETE, Item.class.getName());
        itemRepository.deleteById(id);
    }

    @Override
    public Item create(Item item, Long orderId) {
        if (item.getId() != null) {
            throw new UnprocessableEntityException();
        }
        Order order = orderService.get(orderId);
        if (order.getOrderStatus() == OrderStatus.SHIPPING || order.getOrderStatus() == OrderStatus.DELIVERED) {
            throw new WrongStatusException();
        }
        item.setOrder(order);
        eventService.sendEvent(EventType.CREATE, Item.class.getName());
        return itemRepository.saveAndFlush(item);
    }

    @Override
    public void update(Item item) {
        Item itemDb = itemRepository.findById(item.getId()).orElseThrow(NotFoundException::new);
        item.setOrder(itemDb.getOrder());
        eventService.sendEvent(EventType.UPDATE, Item.class.getName());
        itemRepository.saveAndFlush(item);
    }
}
