package com.example.eshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Item {

    @Id
    @GenericGenerator(name="itemgen" , strategy="increment")
    @GeneratedValue(generator="itemgen")
    private Long id;
    private String name;
    private Long price;
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;
}
