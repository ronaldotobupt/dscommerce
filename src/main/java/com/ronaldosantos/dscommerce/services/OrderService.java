package com.ronaldosantos.dscommerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronaldosantos.dscommerce.dto.OrderDTO;
import com.ronaldosantos.dscommerce.dto.OrderItemDTO;
import com.ronaldosantos.dscommerce.entities.Order;
import com.ronaldosantos.dscommerce.entities.OrderItem;
import com.ronaldosantos.dscommerce.entities.OrderStatus;
import com.ronaldosantos.dscommerce.entities.Product;
import com.ronaldosantos.dscommerce.entities.User;
import com.ronaldosantos.dscommerce.exceptions.ResourceNotFoundException;
import com.ronaldosantos.dscommerce.repositories.OrderItemRepository;
import com.ronaldosantos.dscommerce.repositories.OrderRepository;
import com.ronaldosantos.dscommerce.repositories.ProductRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private UserService userService;
	
	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		Order order = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new OrderDTO(order);
	}
	
	@Transactional(readOnly = true)
	public OrderDTO insert(OrderDTO dto) {
		
		Order order = new Order();
		order.setMoment(Instant.now());
		order.setStatus(OrderStatus.WAITING_PAYMENT);
		
		User user = userService.authenticated();
		order.setClient(user);
		
		for(OrderItemDTO itemDTO : dto.getItems()) {
			Product product = productRepository.getReferenceById(itemDTO.getProductId());
			OrderItem item = new OrderItem(order, product,itemDTO.getQuantity(),product.getPrice());
			order.getItems().add(item);
		}
		
		repository.save(order);
		orderItemRepository.saveAll(order.getItems());
		
		return new OrderDTO(order);
	}

}
