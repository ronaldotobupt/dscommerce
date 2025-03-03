package com.ronaldosantos.dscommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ronaldosantos.dscommerce.dto.ProductDTO;
import com.ronaldosantos.dscommerce.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping(value = "/{id}")
	public ProductDTO findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@GetMapping
	public Page<ProductDTO> findAll(Pageable pageable){
		return service.findAll(pageable);
	}
	
	@PostMapping
	public ProductDTO insert(@RequestBody  ProductDTO dto) {
		return service.insert(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductDTO>update(@PathVariable Long id, @RequestBody  ProductDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok(dto);
	}

}
