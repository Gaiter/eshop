package com.example.eshop.service;

import com.example.eshop.entity.Event;

public interface EventService {
    void handle(Event event);
}
