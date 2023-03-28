package com.example.eshop.api;

import com.example.eshop.entity.Payment;
import com.example.eshop.servise.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/payments", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PaymentRestController {

    private PaymentService paymentService;

    @GetMapping("/{id}")
    public Payment get(@PathVariable Long id) {
        return paymentService.get(id);
    }

    @GetMapping
    public List<Payment> getAll() {
        return paymentService.getAll();
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        paymentService.get(id);
        paymentService.delete(id);
    }

    @PostMapping(value = "/orders/{orderId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    Payment create(@RequestBody Payment payment, @PathVariable Long orderId) {
        return paymentService.create(payment, orderId);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Payment payment) {
        paymentService.update(payment);
    }
}
