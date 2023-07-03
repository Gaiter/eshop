package com.example.eshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Payment {
    @Id
    @GenericGenerator(name="paymentgen" , strategy="increment")
    @GeneratedValue(generator="paymentgen")
    private Long id;
    private Long sum;
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;
}
