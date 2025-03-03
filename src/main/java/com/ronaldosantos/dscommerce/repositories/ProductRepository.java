package com.ronaldosantos.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ronaldosantos.dscommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
