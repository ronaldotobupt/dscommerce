package com.ronaldosantos.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronaldosantos.dscommerce.dto.OrderDTO;
import com.ronaldosantos.dscommerce.entities.Order;
import com.ronaldosantos.dscommerce.exceptions.ResourceNotFoundException;
import com.ronaldosantos.dscommerce.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository repository;
	
	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		Order order = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new OrderDTO(order);
	}

}
