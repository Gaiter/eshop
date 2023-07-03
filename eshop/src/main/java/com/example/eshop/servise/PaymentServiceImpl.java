package com.example.eshop.servise;

import com.example.eshop.entity.Payment;
import com.example.eshop.enums.EventType;
import com.example.eshop.exception.NotFoundException;
import com.example.eshop.exception.UnprocessableEntityException;
import com.example.eshop.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;
    private OrderService orderService;
    private EventService eventService;

    @Override
    public Payment get(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(NotFoundException::new);
        eventService.sendEvent(EventType.GET, Payment.class.toString());
        return payment;
    }

    @Override
    public List<Payment> getAll() {
        List<Payment> payments = paymentRepository.findAll();
        eventService.sendEvent(EventType.GETALL, Payment.class.toString());
        return payments;
    }

    @Override
    public void delete(Long id) {
        eventService.sendEvent(EventType.DELETE, Payment.class.toString());
        paymentRepository.deleteById(id);
    }

    @Override
    public Payment create(Payment payment, Long orderId) {
        if (payment.getId() != null)
            throw new UnprocessableEntityException();
        payment.setOrder(orderService.get(orderId));
        payment.setDateTime(LocalDateTime.now());
        Payment paymentDb = paymentRepository.saveAndFlush(payment);
        eventService.sendEvent(EventType.CREATE, Payment.class.toString());
        return paymentDb;
    }

    @Override
    public void update(Payment payment) {
        Payment paymentDb = get(payment.getId());
        payment.setOrder(paymentDb.getOrder());
        payment.setDateTime(paymentDb.getDateTime());
        eventService.sendEvent(EventType.UPDATE, Payment.class.toString());
        paymentRepository.saveAndFlush(payment);
    }
}
