package com.example.eshop.servise;

import com.example.eshop.entity.Item;

import java.util.List;

public interface ItemService {
    Item get(Long id);

    List<Item> getAll();

    void delete(Long id);

    Item create(Item item, Long id);

    void update(Item item);
}
