package com.example.eshop.entity;

import com.example.eshop.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Event {

    private EventType eventType;
    private String body;
}
