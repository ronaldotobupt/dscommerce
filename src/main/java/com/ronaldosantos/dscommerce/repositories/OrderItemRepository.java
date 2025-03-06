package com.ronaldosantos.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ronaldosantos.dscommerce.entities.OrderItem;
import com.ronaldosantos.dscommerce.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
