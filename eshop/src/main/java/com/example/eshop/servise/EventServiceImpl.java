package com.example.eshop.servise;

import com.example.eshop.entity.Event;
import com.example.eshop.enums.EventType;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class EventServiceImpl implements EventService {

    private KafkaTemplate<String, Event> kafkaTemplate;

    @Override
    public void sendEvent(EventType eventType, String body) {
        kafkaTemplate.sendDefault(new Event(eventType, body));
    }

}
