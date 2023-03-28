package com.example.eshop.service;

import com.example.eshop.entity.Event;
import com.example.eshop.repository.EventRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventKafkaListener implements EventService {

    private EventRepository eventRepository;

    public EventKafkaListener(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    @KafkaListener(topics = "event")
    public void handle(Event event) {
        System.out.println(event.toString());
        eventRepository.saveAndFlush(event);
    }
}
