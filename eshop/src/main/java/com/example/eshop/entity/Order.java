package com.example.eshop.entity;

import com.example.eshop.enums.OrderStatus;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@NamedEntityGraph(
        name = "orders-with-items-payments-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("items"),
                @NamedAttributeNode("payments"),
        }
)
@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@Table(name = "orders")
public class Order {

    @Id
    @GenericGenerator(name="ordergen" , strategy="increment")
    @GeneratedValue(generator="ordergen")
    private Long id;
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;
    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
    private Set<Item> items;
    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
    private Set<Payment> payments;
}
