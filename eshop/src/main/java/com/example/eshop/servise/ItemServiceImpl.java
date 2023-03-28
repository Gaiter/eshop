package com.example.eshop.servise;

import com.example.eshop.entity.Item;
import com.example.eshop.entity.Order;
import com.example.eshop.enums.EventType;
import com.example.eshop.enums.OrderStatus;
import com.example.eshop.exeption.NotFoundException;
import com.example.eshop.exeption.UnprocessableEntityException;
import com.example.eshop.exeption.WrongStatusException;
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

    @Override
    public Item get(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(NotFoundException::new);
        eventService.sendEvent(EventType.GET, Item.class.getName());
        return item;
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
        Item itemDb = get(item.getId());
        item.setOrder(itemDb.getOrder());
        eventService.sendEvent(EventType.UPDATE, Item.class.getName());
        itemRepository.saveAndFlush(item);
    }
}
