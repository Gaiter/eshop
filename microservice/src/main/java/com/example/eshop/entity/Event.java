package com.example.eshop.entity;

import com.example.eshop.enums.EventType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Event {

    @Id
    @GenericGenerator(name="eventgen" , strategy="increment")
    @GeneratedValue(generator="eventgen")
    private Long id;
    @Enumerated
    private EventType eventType;
    private String body;

    public Event(EventType eventType, String body) {
        this.eventType = eventType;
        this.body = body;
    }
}
