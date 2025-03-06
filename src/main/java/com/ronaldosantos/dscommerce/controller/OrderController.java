package com.ronaldosantos.dscommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ronaldosantos.dscommerce.dto.OrderDTO;
import com.ronaldosantos.dscommerce.services.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping(value = "/{id}")
	public OrderDTO findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_CLIENT')")
	@PostMapping
	public OrderDTO insert(@Valid @RequestBody  OrderDTO dto) {
		return service.insert(dto);
	}

}
