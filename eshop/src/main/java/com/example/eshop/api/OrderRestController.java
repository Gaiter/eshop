package com.example.eshop.api;

import com.example.eshop.entity.Order;
import com.example.eshop.servise.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class OrderRestController {

    private OrderService orderService;

    @GetMapping("/{id}")
    public Order get(@PathVariable Long id) {
        return orderService.get(id);
    }

    @GetMapping
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        orderService.get(id);
        orderService.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    Order create(@Validated @RequestBody Order order) {
        return orderService.create(order);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Order order) {
        orderService.update(order);
    }
}
