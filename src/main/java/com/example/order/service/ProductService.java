package com.example.order.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.order.model.product.Product;
import com.example.order.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {
	private final ProductRepository productRepository;
	
	// 상품 상세 정보
	public Product findProductById(Long product_id) {
		return productRepository.findProductById(product_id);
	}
	
	// 상품 목록
	public List<Product> findProducts() {
		return productRepository.findProducts();
	}
}
