package com.example.eshop.service;

import com.example.eshop.entity.Event;
import com.example.eshop.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EventKafkaListener implements EventService {

    private EventRepository eventRepository;

    @Override
    @KafkaListener(topics = "event")
    public void handle(Event event) {
        eventRepository.saveAndFlush(event);
    }
}
