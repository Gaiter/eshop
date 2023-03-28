package com.example.eshop.servise;

import com.example.eshop.enums.EventType;

public interface EventService {
    void sendEvent(EventType eventType, String body);
}
