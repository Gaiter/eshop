package com.example.eshop.api;

import com.example.eshop.dto.ItemDto;
import com.example.eshop.entity.Item;
import com.example.eshop.servise.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ItemRestController {

    private ItemService itemService;

    @GetMapping("/{id}")
    public ItemDto get(@PathVariable Long id) {
        return itemService.get(id);
    }

    @GetMapping
    public List<Item> getAll() {
        return itemService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        itemService.delete(id);
    }

    @PostMapping(value = "/orders/{orderId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    Item create(@Validated @RequestBody Item item, @PathVariable Long orderId) {
        return itemService.create(item, orderId);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Item item) {
        itemService.update(item);
    }
}
