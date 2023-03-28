package com.example.eshop.repository;

import com.example.eshop.entity.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(value = "orders-with-items-payments-entity-graph", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT o FROM Order o WHERE o.id = ?1")
    Optional<Order> getOneWithPaymentsAndOrdersById(Long id);
}
