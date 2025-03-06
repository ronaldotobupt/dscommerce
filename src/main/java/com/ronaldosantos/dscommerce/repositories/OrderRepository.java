package com.ronaldosantos.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ronaldosantos.dscommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
